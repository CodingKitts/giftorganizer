package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.projections.GiftItemMin;

import java.util.Collection;
import java.util.List;

public interface GiftItemService {
    //TODO: Reminder - Make sure to send a Notification to Buyer when Owner deletes a Purchased Item
    //NOTE: No
    /*
        x Create a new GiftItem
        x Get all Minimized GiftItems for Owner
        x Get all Full GiftItems for Friend
        x Update a GiftItem as Owner
        Update a GiftItem as Friend (Purchase)
        Update a GiftItem as Friend (Return)
        x Delete a GiftItem as Owner
        x Delete all GiftItems as Owner
     */

    //ADMIN METHODS
    List<GiftItem> getAllItems();

    //CREATE METHODS
    GiftItem createGiftItem(GiftItem giftItem);

    //READ METHODS
    Collection<GiftItemMin> getAllItemsForOwner(String ownerEmail);
    List<GiftItem> getAllItemsForFriend(String ownerEmail, String friendEmail);

    //UPDATE METHODS
    int updateGiftItem(String ownerEmail, GiftItem giftItem);
    int purchaseGiftItem(String purchaserEmail, Long giftItemId);
    int returnGiftItem(String purchaserEmail, Long giftItemId);


    //DELETE METHODS
    boolean deleteAllGiftItems(String ownerEmail);
    boolean deleteItemById(Long itemId);
}
