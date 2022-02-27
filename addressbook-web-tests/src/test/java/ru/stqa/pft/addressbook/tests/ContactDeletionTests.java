package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test(enabled = false)
    public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContactWithPrecondition(new ContactData("Сергей", "Александрович", "Меньшов", "Рога и копыта", "г. Москва, ул. Советская 4, офис 410", "89456582355", "menshov_am_85@mail.ru", "15", "April", "1985", "Тестовая","г. Москва ул. Невельская, кв.306"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContacts();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}
