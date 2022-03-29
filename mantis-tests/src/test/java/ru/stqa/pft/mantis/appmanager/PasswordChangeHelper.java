package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PasswordChangeHelper extends HelperBase {

    public PasswordChangeHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[value = 'Вход']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[value = 'Вход']"));
    }

    public void goToUsersPage() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }

    public void goToUser() {
        WebElement table = wd.findElement(By.className("table-responsive"));
        List<WebElement> td = table.findElement(By.tagName("tbody")).findElements(By.tagName("a"));
        td.get(1).click();
    }

    public String getUserName(){
        return wd.findElement(By.cssSelector("input[name='username']")).getAttribute("value");
    }

    public String getUserMail(){
        return wd.findElement(By.cssSelector("input[name='email']")).getAttribute("value");
    }

    public void resetUserPassword(){
        wd.findElement(By.cssSelector("input[value='Сбросить пароль']")).click();
    }

    public void goToResetPage(String link) {
        wd.get(link);
    }

    public void setNewPassword(String newPassword) {
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("button[type='submit']"));
    }

}
