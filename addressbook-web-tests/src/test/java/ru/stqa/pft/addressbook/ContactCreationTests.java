package ru.stqa.pft.addressbook;

import java.time.Duration;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactCreationTests {
  private WebDriver dw;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "C:/Windows/System32/chromedriver.exe");
    dw = new ChromeDriver();
    dw.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    dw.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContactData("Сергей", "Александрович", "Меньшов", "Рога и копыта", "г. Москва, ул. Советская 4, офис 410", "89456582355", "menshov_am_85@mail.ru", "15", "April", "1985", "Тестовая", "г. Москва ул. Невельская, кв.306"));
    submitContactCreation();
    returnHomePage();
  }

  private void login(String username, String password) {

    dw.findElement(By.name("user")).click();
    dw.findElement(By.name("user")).clear();
    dw.findElement(By.name("user")).sendKeys(username);
    dw.findElement(By.name("pass")).click();
    dw.findElement(By.name("pass")).clear();
    dw.findElement(By.name("pass")).sendKeys(password);
    dw.findElement(By.xpath("//input[@value='Login']")).click();
  }

  private void initContactCreation() {
    dw.findElement(By.linkText("add new")).click();
  }

  private void fillContactForm(ContactData contactData) {
    dw.findElement(By.name("firstname")).click();
    dw.findElement(By.name("firstname")).clear();
    dw.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    dw.findElement(By.name("middlename")).click();
    dw.findElement(By.name("middlename")).clear();
    dw.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
    dw.findElement(By.name("lastname")).click();
    dw.findElement(By.name("lastname")).clear();
    dw.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    dw.findElement(By.name("company")).click();
    dw.findElement(By.name("company")).clear();
    dw.findElement(By.name("company")).sendKeys(contactData.getCompany());
    dw.findElement(By.name("address")).click();
    dw.findElement(By.name("address")).clear();
    dw.findElement(By.name("address")).sendKeys(contactData.getAddress());
    dw.findElement(By.name("mobile")).click();
    dw.findElement(By.name("mobile")).clear();
    dw.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
    dw.findElement(By.name("email")).click();
    dw.findElement(By.name("email")).clear();
    dw.findElement(By.name("email")).sendKeys(contactData.getEmail());
    dw.findElement(By.name("bday")).click();
    new Select(dw.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
    dw.findElement(By.name("bmonth")).click();
    new Select(dw.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
    dw.findElement(By.name("byear")).click();
    dw.findElement(By.name("byear")).clear();
    dw.findElement(By.name("byear")).sendKeys(contactData.getByear());
    dw.findElement(By.name("new_group")).click();
    new Select(dw.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    dw.findElement(By.name("address2")).click();
    dw.findElement(By.name("address2")).clear();
    dw.findElement(By.name("address2")).sendKeys(contactData.getAddress2());
  }

  private void submitContactCreation() {
    dw.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void returnHomePage() {
    dw.findElement(By.linkText("home page")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    dw.findElement(By.linkText("Logout")).click();
    dw.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      dw.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      dw.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
