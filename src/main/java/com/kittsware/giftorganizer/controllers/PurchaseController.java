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

    @PostMapping("/purchase")
    public Purchase purchaseItem(@RequestBody Purchase purchase) {
        return this.purchaseService.createPurchase(purchase);
    }
}
