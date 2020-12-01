package com.infostackresearch.homefit.models;

public class LoginData {
    String email;
    String password;
    String name;
    String phone;

    public LoginData(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public LoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
