package com.kittsware.giftorganizer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class GiftItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giftItemId;

    private String giftItemName;
    private Double giftItemPrice;
    private String ownerEmail;

    private String buyerEmail = null;
    private boolean isBought = false;

    public GiftItem() {}

}
