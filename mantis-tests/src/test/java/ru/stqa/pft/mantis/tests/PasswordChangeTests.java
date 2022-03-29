package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.*;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class PasswordChangeTests extends TestBase {
    private PasswordChangeHelper passHelper;
    private MailHelper mailHelper;


    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordChange() throws IOException, MessagingException {
        app.passwordChange().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.passwordChange().goToUsersPage();
        app.passwordChange().goToUser();
        String username = app.passwordChange().getUserName();
        String usermail = app.passwordChange().getUserMail();
        app.passwordChange().resetUserPassword();
        List<MailMessage> mailMessage = app.mail().waitForMail(1, 30000);
        String resetLink = findResetLink(mailMessage, usermail);
        System.out.println(resetLink);
        app.passwordChange().goToResetPage(resetLink);
        long now = System.currentTimeMillis();
        String newPassword = String.format("password%s", now);
        app.passwordChange().setNewPassword(newPassword);
        HttpSession session = app.newSession();
        assertTrue(session.login(username, newPassword));
        assertTrue(session.isLoggedInAs(username));

    }

    private String findResetLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}