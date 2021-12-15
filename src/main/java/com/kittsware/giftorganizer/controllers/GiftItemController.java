package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.projections.GiftItemMin;
import com.kittsware.giftorganizer.services.GiftItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
public class GiftItemController {
    private static final Logger logger = LoggerFactory.getLogger(GiftItemController.class);
    private final GiftItemService giftItemService;

    public GiftItemController(GiftItemService giftItemService) {
        this.giftItemService = giftItemService;
    }

    //TODO: Create API methods for the Service functions.
    @GetMapping("/items")
    public List<GiftItem> getAllGiftItemsForOwner(Principal principal, @RequestBody String ownerEmail) {
        return this.giftItemService.getAllItemsForFriend(ownerEmail, principal.getName());
    }

    @GetMapping("/items/owner")
    public Collection<GiftItemMin> getItemsForOwner(Principal principal) {
        return this.giftItemService.getAllItemsForOwner(principal.getName());
    }

    @GetMapping("/admin/items")
    public List<GiftItem> getAllGiftItems() {
        return this.giftItemService.getAllItems();
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
