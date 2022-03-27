package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private RegistrationHelper registrationHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            //wd.findElement(By.linkText("Logout")).click();
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public Object getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.CHROME)) {
                System.setProperty("webdriver.chrome.driver", "C:/Windows/System32/chromedriver.exe");
                wd = new ChromeDriver();

            } else if (browser.equals(BrowserType.OPERA)) {
                System.setProperty("webdriver.opera.driver", "C:/Windows/System32/operadriver.exe");
                wd = new OperaDriver();

            } else if (browser.equals(BrowserType.EDGE)) {
                System.setProperty("webdriver.edge.driver", "C:/Windows/System32/msedgedriver.exe");
                wd = new EdgeDriver();
            }
            wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

}
