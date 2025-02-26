package com.example.login;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private String name;
    private String surname;
    private boolean admin;

    public User(String login, String password, String name, String surname, boolean admin) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.admin = admin;
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

    public String getName() {
        return name;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
