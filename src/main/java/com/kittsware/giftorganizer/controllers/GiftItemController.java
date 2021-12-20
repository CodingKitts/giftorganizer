package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.projections.GiftItemMin;
import com.kittsware.giftorganizer.services.GiftItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @GetMapping("/owner/items")
    public Collection<GiftItemMin> getItemsForOwner(Principal principal) {
        return this.giftItemService.getAllItemsForOwner(principal.getName());
    }

    @GetMapping("/admin/items")
    public List<GiftItem> getAllGiftItems() {
        return this.giftItemService.getAllItems();
    }

    @PutMapping("/item/{giftItemId}")
    public int updateGiftItemAsOwner(@PathVariable Long giftItemId, @RequestBody GiftItem giftItem, Principal principal) {
        return this.giftItemService.updateGiftItem(principal.getName(), giftItem);
    }

    @PostMapping("/item")
    public ResponseEntity<GiftItem> createGiftItem(@Valid @RequestBody GiftItem giftItem, Principal principal) {
        //TODO: Refactor this to consider all return options for saving a new item
        GiftItem gift = this.giftItemService.createGiftItem(principal.getName(), giftItem);

        return new ResponseEntity<>(gift, HttpStatus.CREATED);
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<String> deleteItemById(@NotNull @PathVariable final Long itemId){
        if (this.giftItemService.deleteItemById(itemId)) {
            return new ResponseEntity<>("Deletion Successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Item not Found", HttpStatus.NOT_FOUND);
        }
    }
}
