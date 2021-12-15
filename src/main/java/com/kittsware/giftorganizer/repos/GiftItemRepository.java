package com.kittsware.giftorganizer.repos;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.projections.GiftItemMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GiftItemRepository extends JpaRepository<GiftItem, Long> {

    List<GiftItem> findAllByOwnerEmail(String ownerEmail);
    Collection<GiftItemMin> findGiftItemsByOwnerEmail(String ownerEmail);

    int deleteGiftItemsByOwnerEmail(String ownerEmail);
    int deleteGiftItemByGiftItemId(Long giftItemId);
}
