package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.Purchase;
import com.kittsware.giftorganizer.services.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    //TODO: Okay so the problem is that you are trying to create a Purchase object at a later time than when the GiftItem
    //      was created. However, you would need to retrieve the GiftItem object in the Purchase Service in order to
    //      properly attach the two objects to each other.
    //      I want to keep the purchase service stuff separate from the gift item. So in order to do this, we need to
    //      create a purchase object when the gift item is created. Okay if i do this then I might as well keep purchase
    //      info as part of the Gift Item class... Why did I want to separate the Purchase object?
    //      What if I made them separate objects? I would still need to have a combined service with both repos.
    //
    //      Another option is to completely separate the purchase from the gift. Remove the connection and do a good
    //      job coordinating the two objects..
    @PostMapping("/purchase")
    public Purchase purchaseItem(@RequestBody Purchase purchase) {
        return this.purchaseService.createPurchase(purchase);
    }
}
