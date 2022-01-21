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
        this.validatorService.isValidEmail(friendEmail);
        this.validatorService.friendshipExists(ownerEmail, friendEmail);

        /*if (this.validatorService.friendshipExists(ownerEmail, friendEmail)) {
            //An empty list is sent regardless of whether there are no items, or if the ownerEmail is wrong.
            //We will make checks on the ownerEmail prior to getting info, that way if an empty list is returned, it is
            //only because the list was empty and not because the email is wrong.
            return this.giftItemRepository.findAllByOwnerEmail(ownerEmail);
        }*/
        return this.giftItemRepository.findAllByOwnerEmail(ownerEmail);
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
        if (!this.validatorService.isValidNewItem(ownerEmail, giftItem)) {
            return null;
        }

        return this.giftItemRepository.save(giftItem);
    }

    //DELETE METHODS
    //TODO: Move this functionality into some sort of UserRemoval Service.
    @Override
    @Transactional
    public boolean deleteAllGiftItems(String ownerEmail) {
        //Return true if more than 0 items were deleted.
        //Will return true even if even 1 out of the entire list gets deleted.
        //This function will only ever be used when a User deletes their Account.
        return this.giftItemRepository.deleteGiftItemsByOwnerEmail(ownerEmail) > 0;
    }

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
