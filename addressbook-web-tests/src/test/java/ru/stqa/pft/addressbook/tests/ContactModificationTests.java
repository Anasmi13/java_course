package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().createContactWithPrecondition(new ContactData().withFirstname("Сергей").withMiddlename("Александрович").withLastname("Меньшов").withCompany("Рога и копыта")
                    .withAddress("г. Москва, ул. Советская 4, офис 410").withMobile("89456582355").withEmail("menshov_am_85@mail.ru")
                    .withBday("15").withBmonth("April").withByear("1985").withGroup("Тестовая").withAddress2("г. Москва ул. Невельская, кв.306"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Александр").withMiddlename("Сергеевич").withLastname("Меньшов").withCompany("Рога и копыта")
                .withAddress("г. Москва, ул. Советская 4, офис 410").withMobile("89456582355").withEmail("menshov_am_85@mail.ru")
                .withBday("15").withBmonth("April").withByear("1985").withAddress2("г. Москва ул. Невельская, кв.306");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }

}
