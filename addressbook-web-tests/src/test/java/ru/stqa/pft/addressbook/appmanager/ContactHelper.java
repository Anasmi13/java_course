package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        typeSelect(By.name("bday"), contactData.getBday());
        typeSelect(By.name("bmonth"), contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getHomePhone2());
        attach(By.name("photo"), contactData.getPhoto());
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void returnHomePage() {
        click(By.linkText("home"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        returnHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        returnHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmail = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withAllEmail(allEmail).withAllPhones(allPhones));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String home2 = wd.findElement(By.name("phone2")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withHomePhone2(home2);

    }

    public void addContactToGroup(ContactData addContact, GroupData group) {
        selectContactById(addContact.getId());
        setGroupToAddById(group.getId());
        addToContactToGroup();
    }

    private void setGroupToAddById(int id) {
        click(By.name("to_group"));
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
    }

    private void addToContactToGroup() {
        click(By.name("add"));
    }

    public void deleteContactFromGroup(ContactData deleteContact, GroupData group) {
        setGroupToDeleteById(group.getId());
        selectContactById(deleteContact.getId());
        submitRemoveFromGroup();
    }

    private void setGroupToDeleteById(int id) {
        click(By.name("to_group"));
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(id));
    }

    private void submitRemoveFromGroup() {
        click(By.name("remove"));
    }

}
