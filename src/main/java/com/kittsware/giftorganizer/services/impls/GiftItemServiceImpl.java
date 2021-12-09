package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.projections.GiftItemMin;
import com.kittsware.giftorganizer.repos.GiftItemRepository;
import com.kittsware.giftorganizer.services.GiftItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GiftItemServiceImpl implements GiftItemService {
    private static final Logger logger = LoggerFactory.getLogger(GiftItemServiceImpl.class);
    private final GiftItemRepository giftItemRepository;

    public GiftItemServiceImpl(GiftItemRepository giftItemRepository) {
        this.giftItemRepository = giftItemRepository;
    }

    @Override
    public Collection<GiftItemMin> getAllItemsForOwner(String ownerEmail) {
        return this.giftItemRepository.findGiftItemsByOwnerEmail(ownerEmail);
    }
}
