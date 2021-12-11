package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.entities.Purchase;
import com.kittsware.giftorganizer.repos.PurchaseRepository;
import com.kittsware.giftorganizer.services.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);
    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase createPurchase(Purchase purchase) {
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
