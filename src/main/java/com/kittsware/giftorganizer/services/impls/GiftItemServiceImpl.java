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
        this.validatorService.isValidEmail(ownerEmail);
        this.validatorService.friendshipExists(ownerEmail, friendEmail);

        return this.giftItemRepository.findAllByOwnerEmail(ownerEmail);
    }
    @Override
    public List<GiftItemMin> getAllItemsForOwner(String ownerEmail) {
        this.validatorService.isValidEmail(ownerEmail);

        return this.giftItemRepository.findGiftItemsByOwnerEmail(ownerEmail);
    }
    @Override
    public List<GiftItem> getAllItems() {
        return this.giftItemRepository.findAll();
    }

    //PUT METHODS
    @Override
    public void updateGiftItem(String ownerEmail, GiftItem giftItem) {
        this.validatorService.isItemOwner(ownerEmail, giftItem.getGiftItemId());

        this.giftItemRepository.updateGiftItemAsOwner(giftItem.getGiftItemName(), giftItem.getGiftItemPrice(), giftItem.getGiftItemId());
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
        this.validatorService.isValidEmail(ownerEmail);
        this.validatorService.isValidNewItem(ownerEmail, giftItem);

        return this.giftItemRepository.save(giftItem);
    }

    //DELETE METHODS
    @Override
    @Transactional
    public boolean deleteItemById(Long itemId) {
        //I AM MAKING THE ASSUMPTION THAT A FALSE RETURN VALUE TRANSLATES TO A USER ID NOT BEING FOUND.
        //EVEN THOUGH THERE IS A POSSIBILITY THAT MULTIPLE ITEMS WERE DELETED OR ITEMID BECAME INVALID
        //TODO: Create a Special Notification of Item Deletion for the Friend that Bought the Item.
        //TODO: Create a Notification of Item Deletion for all Friends of the User.
        return this.giftItemRepository.deleteGiftItemByGiftItemId(itemId) == 1;
    }
}
