package com.beans;


import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private PersonInfo info;
    private String login;
    private String password;
    private int id;

    public PersonInfo getInfo() {
        return info;
    }

    public void setInfo(PersonInfo info) {
        this.info = info;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPerson(String name, String surname, String email, String role, String phone) {
        info.setName(name);
        info.setEmail(email);
        info.setPhone(phone);
        info.setRole(role);
        info.setSurname(surname);
    }

    public User(String login, String password, int id) {
        info = new PersonInfo();
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public User() {
        info = new PersonInfo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(info, user.info) &&
                login.equals(user.login) &&
                password.equals(user.password);
    }


    @Override
    public int hashCode() {
        return Objects.hash(info, login, password, id);
    }

    @Override
    public String toString() {
        return "User{" +
                "info=" + info +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
