package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.testng.Assert.assertTrue;

public class DeleteContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            if (groups.size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("Тестовая"));
            }
            File photo = new File("src/test/resources/stru.png");
            app.contact().createContact(new ContactData().withFirstname("Сергей").withMiddlename("Александрович").withLastname("Меньшов").withCompany("Рога и копыта")
                    .withAddress("г. Москва, ул. Советская 4, офис 410").withHomePhone("253678").withMobilePhone("89456582355").withWorkPhone("554968")
                    .withEmail("menshov_am_85@mail.ru").withEmail2("menshov_85@mail.ru").withEmail3("menshov_am@mail.ru")
                    .withBday("15").withBmonth("April").withByear("1985").inGroup(groups.iterator().next())
                    .withAddress2("г. Москва ул. Невельская, кв.306").withHomePhone2("265358").withPhoto(photo));
        }
        if (app.db().contactsInGroup().size() == 0) {
            app.goTo().gotoHomePage();
            app.contact().addContactToGroup(app.db().contacts().iterator().next(), app.db().groups().iterator().next());
        }
    }

    @Test
    public void testDeleteContactFromGroup() {
        Contacts before = app.db().contactsInGroup();
        GroupData group = app.db().groups().iterator().next();
        ContactData deleteContact = before.iterator().next();
        app.contact().deleteContactFromGroup(deleteContact, group);
        Contacts after = app.db().contacts();
        assertTrue(after.iterator().next().getGroups().isEmpty());
    }

}
