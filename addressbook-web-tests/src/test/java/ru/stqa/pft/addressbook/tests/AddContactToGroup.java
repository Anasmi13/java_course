package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        if (groups.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Тестовая"));
        }
        if (app.db().contacts().size() == 0 || app.db().contactsInGroup().size() != 0) {
            File photo = new File("src/test/resources/stru.png");
            app.contact().createContact(new ContactData().withFirstname("Сергей").withMiddlename("Александрович").withLastname("Меньшов").withCompany("Рога и копыта")
                    .withAddress("г. Москва, ул. Советская 4, офис 410").withHomePhone("253678").withMobilePhone("89456582355").withWorkPhone("554968")
                    .withEmail("menshov_am_85@mail.ru").withEmail2("menshov_85@mail.ru").withEmail3("menshov_am@mail.ru")
                    .withBday("15").withBmonth("April").withByear("1985")
                    .withAddress2("г. Москва ул. Невельская, кв.306").withHomePhone2("265358").withPhoto(photo));
        }
    }

    @Test
    public void testAddContactToGroup() {
        GroupData group_before = app.db().groups().iterator().next();
        Contacts contact_before = group_before.getContacts();
        ContactData addContact = app.db().contacts().iterator().next();
        app.contact().addContactToGroup(addContact, group_before);
        GroupData group_after = app.db().groups().iterator().next();
        Contacts contact_after = group_after.getContacts();
        assertThat(contact_after, equalTo(contact_before.withAdded(addContact)));
    }

}
