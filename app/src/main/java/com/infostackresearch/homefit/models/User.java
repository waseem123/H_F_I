package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    String username;
    @SerializedName("token")
    String token;
    @SerializedName("email")
    String email;
    @SerializedName("id")
    String id;

    public User(String username, String token, String email, String id) {
        this.username = username;
        this.token = token;
        this.email = email;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
