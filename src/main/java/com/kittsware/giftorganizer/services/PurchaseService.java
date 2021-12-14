package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.Purchase;

import java.util.List;

public interface PurchaseService {
    //TODO: Brainstorm Purchase Functionality
    //Create a Purchase Item
    //Delete a Purchase Item (Return an Item)
    //Update Purchase when Item is Deleted
    Purchase createPurchase(Purchase purchase);
    boolean deletePurchase(String purchaserEmail, Long purchaseId);
    boolean updatePurchase(Long purchaseId);

    //ADMIN FUNCTIONS
    List<Purchase> getAllPurchases();

    //Do we ever need to update a Purchase Item? Yes. When it's associative Wish Item is deleted.

    //Viewing of the Purchase Information will occur when Gift Items are pulled. We won't need Get methods.


    //TODO: My Problem now is how tied together Puchase and GiftITems are. I need to be able to get a Wish Item with/without
    //      buying information, but also add a purchase separate from its wish item but be able
}
