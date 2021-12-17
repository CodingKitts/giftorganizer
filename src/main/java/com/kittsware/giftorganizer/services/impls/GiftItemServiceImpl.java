package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.projections.GiftItemMin;
import com.kittsware.giftorganizer.repos.GiftItemRepository;
import com.kittsware.giftorganizer.services.GiftItemService;
import com.kittsware.giftorganizer.services.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class GiftItemServiceImpl implements GiftItemService {
    private static final Logger logger = LoggerFactory.getLogger(GiftItemServiceImpl.class);
    private final GiftItemRepository giftItemRepository;
    private final ValidatorService validatorService;

    public GiftItemServiceImpl(GiftItemRepository giftItemRepository, ValidatorService validatorService) {
        this.giftItemRepository = giftItemRepository;
        this.validatorService = validatorService;
    }

    //GET METHODS
    @Override
    public List<GiftItem> getAllItemsForFriend(String ownerEmail, String friendEmail) {
        //NOTE: The ownerEmail is the User's list you are trying to obtain, not the current user.
        if (this.validatorService.areFriends(ownerEmail, friendEmail)) {
            return this.giftItemRepository.findAllByOwnerEmail(ownerEmail);
        }
        return null;
    }
    @Override
    public Collection<GiftItemMin> getAllItemsForOwner(String ownerEmail) {
        return this.giftItemRepository.findGiftItemsByOwnerEmail(ownerEmail);
    }
    @Override
    public List<GiftItem> getAllItems() {
        return this.giftItemRepository.findAll();
    }

    //PUT METHODS
    @Override
    public int updateGiftItem(String ownerEmail, GiftItem giftItem) {
        //Validate that the current User is in fact the Owner of the provided Gift item
        if (this.validatorService.isItemOwner(ownerEmail, giftItem.getGiftItemId())) {
            return 0;
        }

        return this.giftItemRepository.updateGiftItemAsOwner(giftItem.getGiftItemName(), giftItem.getGiftItemPrice(), giftItem.getGiftItemId());
    }

    @Override
    public int purchaseGiftItem(String purchaserEmail, Long giftItemId) {
        //TODO: Create this method
        return 0;
    }

    @Override
    public int returnGiftItem(String purchaserEmail, Long giftItemId) {
        //TODO: Create this method
        return 0;
    }

    //POST METHODS
    @Override
    public GiftItem createGiftItem(String ownerEmail, GiftItem giftItem) {
        //This will check to see if there is an ID already attached to the item, as well as if the Current User & listed Item Owner are the same.
        if (this.validatorService.isValidNewItem(ownerEmail, giftItem)) {
            return null;
        }

        return this.giftItemRepository.save(giftItem);
    }

    //DELETE METHODS
    @Override
    @Transactional
    public boolean deleteAllGiftItems(String ownerEmail) {
        //Return true if more than 0 items were deleted.
        return this.giftItemRepository.deleteGiftItemsByOwnerEmail(ownerEmail) > 0;
    }
    @Override
    @Transactional
    public boolean deleteItemById(Long itemId) {
        //Return true iff 1 item was deleted.
        return this.giftItemRepository.deleteGiftItemByGiftItemId(itemId) == 1;
    }
}
