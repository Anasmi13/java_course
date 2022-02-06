package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Александр", "Сергеевич", "Меньшов", "Рога и копыта", "г. Москва, ул. Советская 4, офис 410", "89456582355", "menshov_am_85@mail.ru", "15", "April", "1985", null,"г. Москва ул. Невельская, кв.306"), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }

}
