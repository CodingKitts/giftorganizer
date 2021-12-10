package com.kittsware.giftorganizer.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class GiftItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giftItemId;

    private String giftItemName;
    private Double giftItemPrice;
    private String ownerEmail;

    //TODO: Can I save a GiftItem without a purchase object?
    @OneToOne
    private Purchase purchase;

    public GiftItem() {}

    public GiftItem(String giftItemName, Double giftItemPrice, String ownerEmail) {
        this.giftItemName = giftItemName;
        this.giftItemPrice = giftItemPrice;
        this.ownerEmail = ownerEmail;
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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
