package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.projections.GiftItemMin;

import java.util.Collection;
import java.util.List;

public interface GiftItemService {
    //TODO: Reminder - Make sure to send a Notification to Buyer when Owner deletes a Purchased Item
    //TODO: What do I need my Service to be able to do?
    /*
        Create a new GiftItem
        Get all Minimized GiftItems for Owner
        Get all Full GiftItems for Friend
        Get a single Minimized GiftItem for Owner
        Get a single Full GiftItem for Friend
        Update a GiftItem as Owner
        Update a GiftItem as Friend (Purchase)
        Update a GiftItem as Friend (Return)
        Delete a GiftItem as Owner
        Delete all GiftItems as Owner
     */

    Collection<GiftItemMin> getAllItemsForOwner(String ownerEmail);
    GiftItem createGiftItem(GiftItem giftItem);
    List<GiftItem> getAllItems();

    boolean deleteAllGiftItems(String ownerEmail);
    boolean deleteItemById(Long itemId);
}
