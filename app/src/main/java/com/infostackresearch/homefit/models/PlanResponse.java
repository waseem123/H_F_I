package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlanResponse {
    @SerializedName("success")
    boolean success;

    @SerializedName("message")
    String message;

    @SerializedName("data")
    List<Plans> plansList;

    public PlanResponse(boolean success, String message, List<Plans> plansList) {
        this.success = success;
        this.message = message;
        this.plansList = plansList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Plans> getPlansList() {
        return plansList;
    }

    public void setPlansList(List<Plans> plansList) {
        this.plansList = plansList;
    }
}
