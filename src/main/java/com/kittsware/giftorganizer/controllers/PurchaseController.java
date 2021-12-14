package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.Purchase;
import com.kittsware.giftorganizer.services.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PurchaseController {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /*
        When you add a Purchase, you already have the GiftItem. The problem is when you want to get a list of giftItems
        for someone that isnt the owner. We need to see Purchase info as well. However when you tie a GiftItem as well,
        You get a circular reference.
     */

    @PostMapping("/purchase")
    public Purchase purchaseItem(@RequestBody Purchase purchase) {
        return this.purchaseService.createPurchase(purchase);
    }

    @GetMapping("/admin/purchases")
    public List<Purchase> getAllPurchases() {
        return this.purchaseService.getAllPurchases();
    }

    //TODO: Create Delete function.
    //TODO: Create Get function? I don't think the users need this. I need to validate I get the purchase info after the
    //      gift item.
}
