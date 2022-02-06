package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    //Проверить, есть ли группа перед созданием контакта
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Тестовая", null, null));
    }
    app.getContactHelper().createContact(new ContactData("Сергей", "Александрович", "Меньшов", "Рога и копыта", "г. Москва, ул. Советская 4, офис 410", "89456582355", "menshov_am_85@mail.ru", "15", "April", "1985", "Тестовая","г. Москва ул. Невельская, кв.306"), true);
  }

}
