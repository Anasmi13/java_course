package ru.stqa.pft.mantis.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.json.TypeToken;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.rest.IssueRest;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpen(int issueId) {
        try {
            return app.soap().isIssueOpened(issueId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isIssueOpenedBugify(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        IssueRest issueRest = new Gson().<List<IssueRest>>fromJson(issues, new TypeToken<List<IssueRest>>() {
            }.getType()).get(0);
        return !(issueRest.getState_name().equals("Resolved") || issueRest.getState_name().equals("Closed"));
    }

    public void skipIfNotFixedBugify(int issueId) {
        try {
            if (isIssueOpenedBugify(issueId)) {
                throw new SkipException("Ignored because of issue " + issueId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
        app.stop();
    }

}
