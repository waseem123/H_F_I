package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class PaymentHash {
    @SerializedName("payment_hash")
    String payment_hash;

    public PaymentHash(String payment_hash) {
        this.payment_hash = payment_hash;
    }

    public String getPayment_hash() {
        return payment_hash;
    }

    public void setPayment_hash(String payment_hash) {
        this.payment_hash = payment_hash;
    }
}
