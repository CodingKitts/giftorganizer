package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.entities.Purchase;
import com.kittsware.giftorganizer.repos.GiftItemRepository;
import com.kittsware.giftorganizer.repos.PurchaseRepository;
import com.kittsware.giftorganizer.services.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    //TODO: In order to save a purchase you will need to get the associated Gift Item.
    private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);
    private final PurchaseRepository purchaseRepository;
    private final GiftItemRepository giftItemRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, GiftItemRepository giftItemRepository) {
        this.purchaseRepository = purchaseRepository;
        this.giftItemRepository = giftItemRepository;
    }

    @Override
    public Purchase createPurchase(Purchase purchase) {

        //TODO: Get the giftItem, add it to the purchase object, save.
        //You will need to figure out how to get the wishItem as well.
        //What if you attach the wish Item to the purchase before sending it to the backend?
        //As a User you are purchasing a known Item, you will have had some access to the item before confirming you
        //bought it. Therefore, you will have access to the item object in the client prior to sending a purchase object
        //to be saved. So, purchase objects should come attached with their wishItems.
        return this.purchaseRepository.save(purchase);
    }

    @Override
    @Transactional
    public boolean deletePurchase(String purchaserEmail, Long purchaseId) {
        //Validate that the PurchaserEmail is the same as the Purchase returned by PurchaseId.
        Purchase purchase = this.purchaseRepository.getById(purchaseId);
        if (purchase.getBuyerEmail().equals(purchaserEmail)) {
            this.purchaseRepository.deleteById(purchaseId);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updatePurchase(Long purchaseId) {
        //TODO: Create a custom function to update a Purchase whenever a User Deletes the Gift Item.
        return false;
    }
}
