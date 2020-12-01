package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class Error {
    @SerializedName("password")
    String passwordError[];

    @SerializedName("email")
    String emailError[];

    @SerializedName("name")
    String nameError[];

    @SerializedName("phone")
    String phoneError[];

    public Error(String[] passwordError, String[] emailError, String[] nameError, String[] phoneError) {
        this.passwordError = passwordError;
        this.emailError = emailError;
        this.nameError = nameError;
        this.phoneError = phoneError;
    }

    public String[] getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String[] passwordError) {
        this.passwordError = passwordError;
    }

    public String[] getEmailError() {
        return emailError;
    }

    public void setEmailError(String[] emailError) {
        this.emailError = emailError;
    }

    public String[] getNameError() {
        return nameError;
    }

    public void setNameError(String[] nameError) {
        this.nameError = nameError;
    }

    public String[] getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String[] phoneError) {
        this.phoneError = phoneError;
    }
}
