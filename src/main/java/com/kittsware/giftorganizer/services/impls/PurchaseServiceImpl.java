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
import java.util.List;
import java.util.Optional;

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
        GiftItem giftItem = this.giftItemRepository.findById(purchase.getGiftItemId()).orElse(null);

        //Check if the ID in the Purchase still has a GiftItem associated with it. This is important because what if a
        //User is making a Purchase for an Item that got deleted by the owner before the User got an updated List.
        if (giftItem == null) {
            return null;
        }

        //We know the giftitem isnt null. So we need to attach the Purchase to the Gift Item.
        //giftItem.setPurchase(purchase);

        //Now save the updated Purchase
        this.giftItemRepository.save(giftItem);

        //Now save the Purchase. We dont actually save the Purchase because GiftItem will do that due to Cascade Type.
        return purchase;
        //return this.purchaseRepository.save(purchase);
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
    public void deletePurchaseById(Long purchaseId) {
        //This isn't working because there is a FK relationship to the GiftItem. The workaround would be to get the Item
        //in question, remove the Purchase, and then resave.
        Purchase purchase = this.purchaseRepository.findById(purchaseId).orElse(null);
        if (purchase == null) {
            return;
        }
        this.purchaseRepository.deleteById(purchaseId);
    }

    @Override
    @Transactional
    public boolean updatePurchase(Long purchaseId) {
        //TODO: Create a custom function to update a Purchase whenever a User Deletes the Gift Item.
        return false;
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return this.purchaseRepository.findAll();
    }
}
