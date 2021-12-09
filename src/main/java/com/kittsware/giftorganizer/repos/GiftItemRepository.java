package com.kittsware.giftorganizer.repos;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.projections.GiftItemMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GiftItemRepository extends JpaRepository<GiftItem, Long> {
    //TODO: Create custom method to return a minimized version of the GiftItem, one with no bought info, return to Owner.

    /*
        Do I need to create a separate Entity class for Purchase Information?
        If yes, I would abstract as much Purchase information from the Gift Item as possible. Things like Buyer Email,
        purchase date, etc. This would make getting an Owner specific version of Gift Items easy, however to get a list
        purchased items as well as the original list would require DB coordination. What if I tie a Purchase object to
        the wish item? Like there is one purchase to one wish item. It would start out null?
     */
    //List<GiftItemMin> findGiftItemsByOwnerEmail(String ownerEmail);
    Collection<GiftItemMin> findGiftItemsByOwnerEmail(String ownerEmail);
}
