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
    //@OneToOne(mappedBy = "giftItem")
    //private Purchase purchase;

    //12.10.21: Refactor to merge purchase back to GiftItem.
    //The reason I wanted them separate was that it is difficult to find objects that a Person has bought to remove when
    //that person deletes their profile. Person A has bought an Item off Person B's list. Person A deletes the app &
    //their profile. Essentially I would need to 1) Search every Gift Item to make sure Person A isnt a buyer. 2) For
    //the items that Person A is a buyer, those Gift Items would need DB Updates.
    //The Alternative is having separate objects. The issue being my stubborness to not want to include multiple repos
    //into a single service class.

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

    /*public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }*/
}
