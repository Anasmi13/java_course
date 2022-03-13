package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {

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
    public void testContactInfo() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contact.getAddress()));
        assertThat(contact.getAllEmail(), equalTo(mergeMail(contactInfoFromEditForm)));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergeMail(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getHomePhone2())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactInfoTests::cleanedPhone)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhone(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
