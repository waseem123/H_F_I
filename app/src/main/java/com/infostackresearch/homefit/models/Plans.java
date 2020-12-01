package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class Plans {
    @SerializedName("id")
    String plan_id;

    @SerializedName("title")
    String title;

    @SerializedName("sub_title")
    String subtitle;

    @SerializedName("description")
    String description;

    @SerializedName("price")
    String price;

    @SerializedName("discount")
    String discount;

    @SerializedName("duration")
    String duration;

    public Plans(String plan_id, String title, String subtitle, String description, String price, String discount, String duration) {
        this.plan_id = plan_id;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.duration = duration;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
