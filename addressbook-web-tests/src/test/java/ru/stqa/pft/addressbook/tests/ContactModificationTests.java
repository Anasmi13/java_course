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
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

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
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Александр").withMiddlename("Сергеевич").withLastname("Меньшов").withCompany("Рога и копыта")
                .withAddress("г. Москва, ул. Советская 4, офис 410").withHomePhone("253678").withMobilePhone("89456582355").withWorkPhone("554968")
                .withEmail("menshov_am_85@mail.ru").withEmail2("menshov_85@mail.ru").withEmail3("menshov_am@mail.ru")
                .withBday("15").withBmonth("April").withByear("1985").withAddress2("г. Москва ул. Невельская, кв.306").withHomePhone2("265358").withPhoto(photo);
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }

}
