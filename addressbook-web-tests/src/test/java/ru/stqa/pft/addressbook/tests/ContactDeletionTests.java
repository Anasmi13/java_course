package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/stru.png");
            app.contact().createContactWithPrecondition(new ContactData().withFirstname("Сергей").withMiddlename("Александрович").withLastname("Меньшов").withCompany("Рога и копыта")
                    .withAddress("г. Москва, ул. Советская 4, офис 410").withHomePhone("253678").withMobilePhone("89456582355").withWorkPhone("554968")
                    .withEmail("menshov_am_85@mail.ru").withEmail2("menshov_85@mail.ru").withEmail3("menshov_am@mail.ru")
                    .withBday("15").withBmonth("April").withByear("1985").withGroup("Тестовая").withAddress2("г. Москва ул. Невельская, кв.306").withHomePhone2("265358").withPhoto(photo));
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInUI();
    }

}
