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

    @Override
    public GiftItem createGiftItem(GiftItem giftItem) {
        logger.info("GIFT ITEM: "+giftItem.getGiftItemName());
        return this.giftItemRepository.save(giftItem);
    }

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
