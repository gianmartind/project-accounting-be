package com.gmd.project_accounting_be.modules.purchase_item.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gmd.project_accounting_be.core.utils.CommonUtil;
import com.gmd.project_accounting_be.modules.purchase_item.dto.request.GetPurchaseItemListRequestDTO;
import com.gmd.project_accounting_be.modules.purchase_item.dto.response.projections.PurchaseItemListRecordResponseDTO;
import com.gmd.project_accounting_be.modules.purchase_item.repositories.PurchaseItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseItemService {
    private final PurchaseItemRepository purchaseItemRepository;

    public Page<PurchaseItemListRecordResponseDTO> getPurchaseItemRecordList(GetPurchaseItemListRequestDTO param) {
        Sort sort = CommonUtil.generateSort(param.getSort());
        Pageable pageable = PageRequest.of(param.getPage(), param.getSize(), sort);
        return purchaseItemRepository.findAllPurchaseItemRecord(param, pageable);
    }

    public List<String> getAllItemTypes() {
        try {
            return purchaseItemRepository.findAllDistinctTypes();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<String> getAllItemUnits() {
        try {
            return purchaseItemRepository.findAllDistinctUnits();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<String> getAllItemCategories() {
        try {
            return purchaseItemRepository.findAllDistinctCategory();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<String> getAllItemBrands() {
        try {
            return purchaseItemRepository.findAllDistinctBrand();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
