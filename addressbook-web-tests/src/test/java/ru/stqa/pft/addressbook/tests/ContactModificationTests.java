package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContactWithPrecondition(new ContactData("Сергей", "Александрович", "Меньшов", "Рога и копыта", "г. Москва, ул. Советская 4, офис 410", "89456582355", "menshov_am_85@mail.ru", "15", "April", "1985", "Тестовая","г. Москва ул. Невельская, кв.306"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Александр", "Сергеевич", "Меньшов", "Рога и копыта", "г. Москва, ул. Советская 4, офис 410", "89456582355", "menshov_am_85@mail.ru", "15", "April", "1985", null,"г. Москва ул. Невельская, кв.306");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
