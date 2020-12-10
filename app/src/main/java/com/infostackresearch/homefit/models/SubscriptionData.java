package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class SubscriptionData {
    @SerializedName("id")
    String id;

    @SerializedName("user_id")
    String user_id;

    @SerializedName("subscription_id")
    String subscription_id;

    @SerializedName("price")
    String price;

    @SerializedName("paymentid")
    String paymentid;

    @SerializedName("start_date")
    String start_date;

    @SerializedName("end_date")
    String end_date;

    @SerializedName("is_expired")
    String is_expired;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("updated_at")
    String updated_at;

    public SubscriptionData(String id, String user_id, String subscription_id, String price, String paymentid, String start_date, String end_date, String is_expired, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.subscription_id = subscription_id;
        this.price = price;
        this.paymentid = paymentid;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_expired = is_expired;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(String subscription_id) {
        this.subscription_id = subscription_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getIs_expired() {
        return is_expired;
    }

    public void setIs_expired(String is_expired) {
        this.is_expired = is_expired;
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
}
