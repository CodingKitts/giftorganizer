package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.projections.GiftItemMin;

import java.util.Collection;

public interface GiftItemService {
    Collection<GiftItemMin> getAllItemsForOwner(String ownerEmail);
}
