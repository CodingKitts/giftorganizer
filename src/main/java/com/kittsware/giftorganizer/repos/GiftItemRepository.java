package com.kittsware.giftorganizer.repos;

import com.kittsware.giftorganizer.entities.GiftItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftItemRepository extends JpaRepository<GiftItem, Long> {
    //TODO: Create custom method to return a minimized version of the GiftItem, one with no bought info, return to Owner.

}
