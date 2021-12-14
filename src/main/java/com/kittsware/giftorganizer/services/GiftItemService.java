package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.projections.GiftItemMin;

import java.util.Collection;
import java.util.List;

public interface GiftItemService {
    Collection<GiftItemMin> getAllItemsForOwner(String ownerEmail);
    GiftItem createGiftItem(GiftItem giftItem);
    List<GiftItem> getAllItems();
    //What else do I need to be able to do with Wish Items?
    /*
        Get Items as a Friend
        Update Item as an Owner
        Update Item as a Friend (Purchase)
        Delete an Item as an Owner

     */

    boolean deleteAllGiftItems(String ownerEmail);
    boolean deleteItemById(Long itemId);
}
