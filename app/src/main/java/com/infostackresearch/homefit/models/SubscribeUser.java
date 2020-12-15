package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class SubscribeUser {
    @SerializedName("success")
    boolean success;

    @SerializedName("message")
    String message;

    @SerializedName("sp")
    SubscriptionData subscriptionData;

    @SerializedName("order")
    OrderData orderData;

    public SubscribeUser(boolean success, String message, SubscriptionData subscriptionData, OrderData orderData) {
        this.success = success;
        this.message = message;
        this.subscriptionData = subscriptionData;
        this.orderData = orderData;
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

    public SubscriptionData getSubscriptionData() {
        return subscriptionData;
    }

    public void setSubscriptionData(SubscriptionData subscriptionData) {
        this.subscriptionData = subscriptionData;
    }

    public OrderData getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderData orderData) {
        this.orderData = orderData;
    }
}
