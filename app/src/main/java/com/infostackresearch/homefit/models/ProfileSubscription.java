package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileSubscription {
    @SerializedName("user")
    User user;

    @SerializedName("data")
    List<SubscriptionData> subscriptionData;

    @SerializedName("success")
    boolean success;

    @SerializedName("message")
    String message;

    public ProfileSubscription(User user, List<SubscriptionData> subscriptionData, boolean success, String message) {
        this.user = user;
        this.subscriptionData = subscriptionData;
        this.success = success;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SubscriptionData> getSubscriptionData() {
        return subscriptionData;
    }

    public void setSubscriptionData(List<SubscriptionData> subscriptionData) {
        this.subscriptionData = subscriptionData;
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
}
