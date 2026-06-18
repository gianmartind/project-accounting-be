package com.gmd.project_accounting_be.modules.purchase.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gmd.project_accounting_be.modules.purchase.dto.response.projections.PurchaseSimpleDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmd.project_accounting_be.core.utils.CommonUtil;
import com.gmd.project_accounting_be.modules.purchase.dto.request.GetPurchaseListRecordRequestDTO;
import com.gmd.project_accounting_be.modules.purchase.dto.request.UpsertPurchaseRequestDTO;
import com.gmd.project_accounting_be.modules.purchase.dto.response.PurchaseDetailResponseDTO;
import com.gmd.project_accounting_be.modules.purchase.dto.response.projections.PurchaseListRecordResponseDTO;
import com.gmd.project_accounting_be.modules.purchase.entities.Purchase;
import com.gmd.project_accounting_be.modules.purchase.repositories.PurchaseRepository;
import com.gmd.project_accounting_be.modules.purchase_item.entities.PurchaseItem;
import com.gmd.project_accounting_be.modules.purchase_item.repositories.PurchaseItemRepository;
import com.gmd.project_accounting_be.modules.store.entities.Store;
import com.gmd.project_accounting_be.modules.store.repositories.StoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final StoreRepository storeRepository;

    public Page<PurchaseListRecordResponseDTO> getPurchaseRecordList(GetPurchaseListRecordRequestDTO param) {
        Sort sort = CommonUtil.generateSort(param.getSort());
        Pageable pageable = PageRequest.of(param.getPage(), param.getSize(), sort);
        return purchaseRepository.findAllPurchaseRecord(param, pageable);
    }

    @Transactional
    public Purchase insert(UpsertPurchaseRequestDTO body) {
        String storeUuid;
        storeUuid = findOrInsertStoreByName(body.getStoreName());
        Purchase toInsert = Purchase.builder()
                        .purchaseDate(body.getPurchaseDate())
                        .storeUuid(storeUuid)
                        .projectUuid(body.getProjectUuid())
                        .notes(body.getNotes())
                        .build();
        Purchase inserted = purchaseRepository.save(toInsert);
        List<PurchaseItem> itemsToInsert = new ArrayList<>(body.getItems());
        assignPurchaseUuidToItems(inserted.getUuid(), itemsToInsert);
        purchaseItemRepository.saveAll(itemsToInsert);
        return inserted;
    }

    public PurchaseDetailResponseDTO getDetail(String uuid) {
        PurchaseSimpleDetailDTO simpleDetail = purchaseRepository.findSimpleDetailById(uuid);
        List<PurchaseItem> items = purchaseItemRepository.findAllByPurchaseUuid(uuid);
        return PurchaseDetailResponseDTO.builder()
                .uuid(simpleDetail.getUuid())
                .purchaseDate(simpleDetail.getPurchaseDate())
                .storeName(simpleDetail.getStoreName())
                .projectUuid(simpleDetail.getProjectUuid())
                .notes(simpleDetail.getNotes())
                .items(items)
                .build();
    }

    public PurchaseDetailResponseDTO updateByUuid(String uuid, UpsertPurchaseRequestDTO body) {
        Optional<Purchase> existing = purchaseRepository.findById(uuid);
        if (existing.isPresent()) {
            Purchase existingData = existing.get();

            String newStoreUuid = findOrInsertStoreByName(body.getStoreName());
            existingData.setStoreUuid(newStoreUuid);
            existingData.setPurchaseDate(body.getPurchaseDate());
            existingData.setProjectUuid(body.getProjectUuid());
            existingData.setNotes(body.getNotes());
            purchaseRepository.save(existingData);

            // delete all purchase items of this purchase
            List<PurchaseItem> existingItems = purchaseItemRepository.findAllByPurchaseUuid(uuid);
            purchaseItemRepository.deleteAll(existingItems);

            // re-insert purchase items with items from body
            List<PurchaseItem> itemsToInsert = new ArrayList<>(body.getItems());
            assignPurchaseUuidToItems(uuid, itemsToInsert);
            purchaseItemRepository.saveAll(itemsToInsert);

            return PurchaseDetailResponseDTO.builder()
                    .uuid(existingData.getUuid())
                    .purchaseDate(existingData.getPurchaseDate())
                    .storeName(body.getStoreName())
                    .projectUuid(existingData.getProjectUuid())
                    .notes(existingData.getNotes())
                    .items(itemsToInsert)
                    .build();
        }
        return null;
    }

    @Transactional
    public void deleteByUuid(String uuid) {
        Optional<Purchase> existing = purchaseRepository.findById(uuid);
        purchaseItemRepository.deleteByPurchaseUuid(uuid);
        existing.ifPresent(purchaseRepository::delete);
    }

    private void assignPurchaseUuidToItems(String purchaseUuid, List<PurchaseItem> items) {
        for (PurchaseItem item : items) {
            item.setPurchaseUuid(purchaseUuid);
        }
    }

    private String findOrInsertStoreByName(String storeName) {
        Optional<Store> storeOpt = storeRepository.findByName(storeName);
        if (storeOpt.isPresent()) {
            return storeOpt.get().getUuid();
        } else {
            Store insertedStore = storeRepository.save(
                    Store.builder()
                            .name(storeName)
                            .build());
            return insertedStore.getUuid();
        }
    }
}
