package com.kittsware.giftorganizer.repos;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.projections.GiftItemMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GiftItemRepository extends JpaRepository<GiftItem, Long> {

    List<GiftItem> findAllByOwnerEmail(String ownerEmail);
    Collection<GiftItemMin> findGiftItemsByOwnerEmail(String ownerEmail);

    @Modifying
    @Query(value = "update gift_item g set g.gift_item_name = :giftItemName and g.gift_item_price = :giftItemPrice where g.gift_item_id = :giftItemId", nativeQuery = true)
    int updateGiftItemAsOwner(String giftItemName, Double giftItemPrice, Long giftItemId);

    int deleteGiftItemsByOwnerEmail(String ownerEmail);
    int deleteGiftItemByGiftItemId(Long giftItemId);
}
