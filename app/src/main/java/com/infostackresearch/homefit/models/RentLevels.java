package com.infostackresearch.homefit.models;

public class RentLevels {
    int id;
    String levelname;
    int imageresource;
    String amount;

    public RentLevels(int id, String levelname, int imageresource, String amount) {
        this.id = id;
        this.levelname = levelname;
        this.imageresource = imageresource;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelname;
    }

    public void setLevelName(String levelname) {
        this.levelname = levelname;
    }

    public int getImageResource() {
        return imageresource;
    }

    public void setImageResource(int imageresource) {
        this.imageresource = imageresource;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
