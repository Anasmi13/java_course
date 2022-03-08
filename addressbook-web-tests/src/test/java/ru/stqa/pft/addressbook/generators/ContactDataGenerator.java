package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstname(String.format("Сергей %s", i)).withMiddlename(String.format("Александрович %s", i)).withLastname(String.format("Меньшов %s", i))
                    .withCompany(String.format("Рога и копыта %s", i)).withAddress(String.format("г. Москва, ул. Советская 4, офис 41%s", i))
                    .withHomePhone(String.format("25367%s", i)).withMobilePhone(String.format("8945658235%s", i)).withWorkPhone(String.format("55496%s", i))
                    .withEmail(String.format("menshov_am_8%s@mail.ru", i)).withEmail2(String.format("menshov_8%s@mail.ru", i)).withEmail3(String.format("menshov_am8%s@mail.ru", i))
                    .withAddress2(String.format("г. Москва ул. Невельская, кв.30%s", i)).withHomePhone2(String.format("26535%s", i)));
        }
        return contacts;
    }

}
