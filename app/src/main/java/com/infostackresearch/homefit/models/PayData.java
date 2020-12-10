package com.infostackresearch.homefit.models;

public class PayData {
    String offerKey;
    String cardBin;

    public PayData(String offerKey, String cardBin) {
        this.offerKey = offerKey;
        this.cardBin = cardBin;
    }

    public String getOfferKey() {
        return offerKey;
    }

    public void setOfferKey(String offerKey) {
        this.offerKey = offerKey;
    }

    public String getCardBin() {
        return cardBin;
    }

    public void setCardBin(String cardBin) {
        this.cardBin = cardBin;
    }
}
