package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Сергей").withMiddlename("Александрович").withLastname("Меньшов").withCompany("Рога и копыта")
            .withAddress("г. Москва, ул. Советская 4, офис 410").withHomePhone("253678").withMobilePhone("89456582355").withWorkPhone("554968")
            .withEmail("menshov_am_85@mail.ru").withBday("15").withBmonth("April").withByear("1985").withGroup("Тестовая").withAddress2("г. Москва ул. Невельская, кв.306");
    app.contact().createContactWithPrecondition(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

}
