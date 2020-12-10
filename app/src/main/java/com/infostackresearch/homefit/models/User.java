package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    String username;

    @SerializedName("name")
    String name;

    @SerializedName("phone")
    String phone;

    @SerializedName("email_verified_at")
    String email_verified_at;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("updated_at")
    String updated_at;

    @SerializedName("active_status")
    String active_status;

    @SerializedName("token")
    String token;
    @SerializedName("email")
    String email;
    @SerializedName("id")
    String id;

    public User(String name, String phone, String email_verified_at, String created_at, String updated_at, String active_status, String email, String id) {
        this.name = name;
        this.phone = phone;
        this.email_verified_at = email_verified_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.active_status = active_status;
        this.email = email;
        this.id = id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getActive_status() {
        return active_status;
    }

    public void setActive_status(String active_status) {
        this.active_status = active_status;
    }
}
