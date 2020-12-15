package com.infostackresearch.homefit.models;

public class DeliveryLocation {
    String addressid;
    String address;
    String deliverto;

    public DeliveryLocation(String addressid, String address, String deliverto) {
        this.addressid = addressid;
        this.address = address;
        this.deliverto = deliverto;
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliverto() {
        return deliverto;
    }

    public void setDeliverto(String deliverto) {
        this.deliverto = deliverto;
    }
}
