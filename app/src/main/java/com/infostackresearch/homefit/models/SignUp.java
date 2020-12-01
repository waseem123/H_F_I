package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class SignUp {
    @SerializedName("status_code")
    String status_code;

    @SerializedName("access_token")
    String access_token;

    @SerializedName("error")
    Error error;

    @SerializedName("success")
    Boolean success;

    public SignUp(String status_code, String access_token) {
        this.status_code = status_code;
        this.access_token = access_token;
    }

    public SignUp(Error error, Boolean success) {
        this.error = error;
        this.success = success;
    }

    public SignUp(String status_code, String access_token, Error error, Boolean success) {
        this.status_code = status_code;
        this.access_token = access_token;
        this.error = error;
        this.success = success;
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

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
