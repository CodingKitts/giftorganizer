package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.exceptions.InvalidEmailException;
import com.kittsware.giftorganizer.exceptions.InvalidItemException;
import com.kittsware.giftorganizer.projections.GiftItemMin;
import com.kittsware.giftorganizer.services.GiftItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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

    @GetMapping("/items")
    public ResponseEntity<List<GiftItem>> getAllGiftItemsForFriend(@RequestBody String ownerEmail, Principal principal) {
        List<GiftItem> items = this.giftItemService.getAllItemsForFriend(ownerEmail, principal.getName());
        if (items == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/owner/items")
    public ResponseEntity<List<GiftItemMin>> getItemsForOwner(Principal principal) {
        try {
            List<GiftItemMin> items = this.giftItemService.getAllItemsForOwner(principal.getName());
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (RuntimeException e) {
            if (e.getClass().equals(InvalidEmailException.class)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
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
        try {
            GiftItem gift = this.giftItemService.createGiftItem(principal.getName(), giftItem);
            return new ResponseEntity<>(gift, HttpStatus.OK);
        } catch (RuntimeException e) {
            if (e.getClass().equals(InvalidEmailException.class) || e.getClass().equals(InvalidItemException.class)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
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
