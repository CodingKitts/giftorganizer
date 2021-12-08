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

    public GiftItem() {}

    public GiftItem(String giftItemName, Double giftItemPrice) {
        this.giftItemName = giftItemName;
        this.giftItemPrice = giftItemPrice;
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
}
