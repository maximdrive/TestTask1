package com.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;
    private String email;
    private ArrayList<String> role;
    private ArrayList<String> phone;

    public PersonInfo(String name, String surname, String email, String role, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = new ArrayList<>(3);
        this.role.add(role);
        this.phone = new ArrayList<>(3);
        this.phone.add(phone);
    }

    public PersonInfo() {
        this.role = new ArrayList<>(3);
        this.phone = new ArrayList<>(3);
        name = "";
        surname = "";
        email = "";
    }

    public String getName() {
        return name == null ? null : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role.add(role);
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.add(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonInfo that = (PersonInfo) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(role, that.role) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, role, phone);
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", phone=" + phone +
                '}';
    }

    public static boolean isEmailCorrect(String mail) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    public static boolean isPhoneCorrect(String phone) {
        Pattern pattern = Pattern.compile("(\\+*)375\\d{9}");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
