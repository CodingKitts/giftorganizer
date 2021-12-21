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

        if (!this.validatorService.isValidEmail(ownerEmail)) {
            //TODO: Refactor this to throw an exception that we can process. This happens when the email is empty, invalid format, or no associated user.
            return null;
        }


        if (this.validatorService.areFriends(ownerEmail, friendEmail)) {
            //Okay so when OwnerEmail is null an empty List is still sent.
            //What about when the email is wrong? Empty List
            //So do I care if the OwnerEmail gets messed up before pulling? No
            return this.giftItemRepository.findAllByOwnerEmail(ownerEmail);
        }
        logger.info("VALIDATOR FAILED TO CONFIRM THEY ARE FRIENDS");
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
