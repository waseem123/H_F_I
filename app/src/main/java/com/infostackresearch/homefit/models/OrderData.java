package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class OrderData {
    @SerializedName("user_id")
    String user_id;

    @SerializedName("order_no")
    String order_no;

    @SerializedName("user_subscription_id")
    String user_subscription_id;

    @SerializedName("order_date")
    String order_date;

    @SerializedName("status")
    String status;

    @SerializedName("updated_at")
    String updated_at;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("id")
    String id;


    public OrderData(String user_id, String order_no, String user_subscription_id, String order_date, String status, String updated_at, String created_at, String id) {
        this.user_id = user_id;
        this.order_no = order_no;
        this.user_subscription_id = user_subscription_id;
        this.order_date = order_date;
        this.status = status;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getUser_subscription_id() {
        return user_subscription_id;
    }

    public void setUser_subscription_id(String user_subscription_id) {
        this.user_subscription_id = user_subscription_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
