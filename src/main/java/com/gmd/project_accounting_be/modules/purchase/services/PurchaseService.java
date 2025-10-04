package com.gmd.project_accounting_be.modules.purchase.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmd.project_accounting_be.modules.purchase.dto.request.UpsertPurchaseDTO;
import com.gmd.project_accounting_be.modules.purchase.dto.response.PurchaseDetailDTO;
import com.gmd.project_accounting_be.modules.purchase.entities.Purchase;
import com.gmd.project_accounting_be.modules.purchase.entities.PurchaseItem;
import com.gmd.project_accounting_be.modules.purchase.repositories.PurchaseItemRepository;
import com.gmd.project_accounting_be.modules.purchase.repositories.PurchaseRepository;
import com.gmd.project_accounting_be.modules.store.entities.Store;
import com.gmd.project_accounting_be.modules.store.repositories.StoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public UpsertPurchaseDTO insert(UpsertPurchaseDTO body) {
        String storeUuid = "";
        Optional<Store> storeOpt = storeRepository.findByName(body.getStoreName());
        if (storeOpt.isPresent()) {
            storeUuid = storeOpt.get().getUuid();
        } else {
            Store insertedStore = storeRepository.save(
                    Store.builder()
                            .name(body.getStoreName())
                            .build());
            storeUuid = insertedStore.getUuid();
        }
        Purchase inserted = purchaseRepository.save(
                Purchase.builder()
                        .purchaseDate(body.getDate())
                        .storeUuid(storeUuid)
                        .projectUuid(body.getProjectUuid())
                        .notes(body.getNotes())
                        .build());
        List<PurchaseItem> itemsToInsert = new ArrayList<>();
        for (PurchaseItem item : body.getItems()) {
            item.setPurchaseUuid(inserted.getUuid());
            itemsToInsert.add(item);
        }
        purchaseItemRepository.saveAll(itemsToInsert);
        return body;
    }

    public PurchaseDetailDTO getDetail(String uuid) {
        Optional<Purchase> purchaseOpt = purchaseRepository.findById(uuid);
        if (purchaseOpt.isEmpty()) {
            return null;
        }
        Purchase purchase = purchaseOpt.get();
        Optional<Store> storeOpt = storeRepository.findById(purchase.getStoreUuid());
        String storeName = storeOpt.map(Store::getName).orElse("");
        List<PurchaseItem> items = purchaseItemRepository.findAllByPurchaseUuid(uuid);
        return PurchaseDetailDTO.builder()
                .uuid(purchase.getUuid())
                .purchaseDate(purchase.getPurchaseDate())
                .storeName(storeName)
                .projectUuid(purchase.getProjectUuid())
                .notes(purchase.getNotes())
                .items(items)
                .build();
    }

    public List<String> getAllItemTypes() {
        try {
            return purchaseItemRepository.findAllDistinctTypes();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
