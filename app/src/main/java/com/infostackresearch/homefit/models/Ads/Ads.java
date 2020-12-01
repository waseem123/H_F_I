package com.infostackresearch.homefit.models.Ads;

public class Ads {
    int id;
    String text;
    int imageID;

    public Ads(int id, String text, int imageID) {
        this.id = id;
        this.text = text;
        this.imageID = imageID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
