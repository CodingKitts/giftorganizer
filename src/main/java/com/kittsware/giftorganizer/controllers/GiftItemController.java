package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.projections.GiftItemMin;
import com.kittsware.giftorganizer.services.GiftItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class GiftItemController {
    private static final Logger logger = LoggerFactory.getLogger(GiftItemController.class);
    private final GiftItemService giftItemService;

    public GiftItemController(GiftItemService giftItemService) {
        this.giftItemService = giftItemService;
    }

    @GetMapping("/items")
    public Collection<GiftItemMin> getItemsForOwner(@RequestBody String ownerEmail) {
        return this.giftItemService.getAllItemsForOwner(ownerEmail);
    }

    @PostMapping("/item")
    public GiftItem createGiftItem(@RequestBody GiftItem giftItem) {
        return this.giftItemService.createGiftItem(giftItem);
    }

    @DeleteMapping("/items")
    public boolean deleteAllGiftItems(@RequestBody String ownerEmail) {
        return this.giftItemService.deleteAllGiftItems(ownerEmail);
    }

    @DeleteMapping("/item/{itemId}")
    public boolean deleteItemById(@PathVariable Long itemId) {
        return this.giftItemService.deleteItemById(itemId);
    }
}
