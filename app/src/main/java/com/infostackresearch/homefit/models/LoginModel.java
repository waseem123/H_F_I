package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("status_code")
    String status_code;
    @SerializedName("access_token")
    String access_token;
    @SerializedName("user")
    User user;
    @SerializedName("role")
    String role;
    @SerializedName("token_type")
    String token_type;

    public LoginModel(String status_code, String access_token, User user, String role, String token_type) {
        this.status_code = status_code;
        this.access_token = access_token;
        this.user = user;
        this.role = role;
        this.token_type = token_type;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
