package com.kittsware.giftorganizer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GiftItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giftItemId;

    private String giftItemName;
    private Double giftItemPrice;
    private boolean isBought;
    private String ownerEmail;
    private String buyerEmail;

    public GiftItem() {}

    public GiftItem(String giftItemName, Double giftItemPrice, String ownerEmail) {
        this.giftItemName = giftItemName;
        this.giftItemPrice = giftItemPrice;
        this.ownerEmail = ownerEmail;
        this.buyerEmail = null;
        this.isBought = false;
    }

    public Long getGiftItemId() {
        return giftItemId;
    }

    public void setGiftItemId(Long giftItemId) {
        this.giftItemId = giftItemId;
    }

    public String getGiftItemName() {
        return giftItemName;
    }

    public void setGiftItemName(String giftItemName) {
        this.giftItemName = giftItemName;
    }

    public Double getGiftItemPrice() {
        return giftItemPrice;
    }

    public void setGiftItemPrice(Double giftItemPrice) {
        this.giftItemPrice = giftItemPrice;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }
}
