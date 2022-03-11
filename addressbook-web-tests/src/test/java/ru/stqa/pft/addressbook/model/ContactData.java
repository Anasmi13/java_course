package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    @Column(name = "middlename")
    private String middlename;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Column(name = "company")
    private String company;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Transient
    private String allPhones;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String allEmail;

    @Expose
    @Transient
    private String bday;

    @Expose
    @Transient
    private String bmonth;

    @Expose
    @Transient
    private String byear;

    @Expose
    @Transient
    private String group;

    @Expose
    @Column(name = "address2")
    @Type(type = "text")
    private String address2;

    @Expose
    @Column(name = "phone2")
    @Type(type = "text")
    private String homePhone2;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    public ContactData withBday(String bday) {
        this.bday = bday;
        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public ContactData withHomePhone2(String homePhone2) {
        this.homePhone2 = homePhone2;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmail() {
        return allEmail;
    }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }

    public String getGroup() {
        return group;
    }

    public String getAddress2() {
        return address2;
    }

    public String getHomePhone2() {
        return homePhone2;
    }

    public File getPhoto() {
        return new File(photo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

}
