package com.infostackresearch.homefit.models;

public class Equipments {
    int id;
    String equipment_name;
    int imageResource;
    String quantity;

    public Equipments(int id, String equipment_name, int imageResource, String quantity) {
        this.id = id;
        this.equipment_name = equipment_name;
        this.imageResource = imageResource;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
