package com.kittsware.giftorganizer.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    private String buyerEmail;
    private Double purchasePrice;
    private boolean wasDeleted = false;

    //mappedBy is the name of the field in the other Class?
    @NotNull
    @OneToOne(mappedBy = "purchase")
    private GiftItem giftItem;

    public Purchase() {}

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public boolean isWasDeleted() {
        return wasDeleted;
    }

    public void setWasDeleted(boolean wasDeleted) {
        this.wasDeleted = wasDeleted;
    }

    public GiftItem getGiftItem() {
        return giftItem;
    }

    public void setGiftItem(GiftItem giftItem) {
        this.giftItem = giftItem;
    }
}
