package com.kittsware.giftorganizer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
public class GiftItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giftItemId;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 2)
    private Double giftItemPrice;

    @NotEmpty(message = "Gift Item Name may not be empty")
    @Size(min = 2, max = 32, message = "Gift Item Name must be between 2 and 32 characters long")
    private String giftItemName;

    @NotEmpty(message = "Owner Email must not empty")
    private String ownerEmail;

    private String buyerEmail;
    private boolean isBought;

    public GiftItem() {}

}
