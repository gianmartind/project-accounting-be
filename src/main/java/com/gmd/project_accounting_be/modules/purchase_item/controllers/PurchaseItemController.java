package com.gmd.project_accounting_be.modules.purchase_item.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmd.project_accounting_be.modules.purchase_item.dto.request.GetPurchaseItemListRequestDTO;
import com.gmd.project_accounting_be.modules.purchase_item.dto.response.projections.PurchaseItemListRecordResponseDTO;
import com.gmd.project_accounting_be.modules.purchase_item.services.PurchaseItemService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/purchase-item")
@RequiredArgsConstructor
public class PurchaseItemController {
    private final PurchaseItemService purchaseItemService;

    @GetMapping("/list")
    public Page<PurchaseItemListRecordResponseDTO> getPurchaseItemList(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String type,
        @RequestParam(required = false) String unit,
        @RequestParam(required = false) String brand,
        @RequestParam(required = false) String category,
        @RequestParam(name = "project_name", required = false) String projectName,
        @RequestParam(name = "project_uuid", required = false) String projectUuid,
        @RequestParam(name = "store_name", required = false) String storeName,
        @RequestParam(name = "store_uuid", required = false) String storeUuid,
        @RequestParam(name = "purchase_date_from", required = false) LocalDate purchaseDateFrom,
        @RequestParam(name = "purchase_date_to", required = false) LocalDate purchaseDateTo,
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size
    ) {
        try {
            GetPurchaseItemListRequestDTO param = GetPurchaseItemListRequestDTO.builder()
                .name(name)
                .type(type)
                .unit(unit)
                .brand(brand)
                .category(category)
                .projectName(projectName)
                .projectUuid(projectUuid)
                .storeName(storeName)
                .storeUuid(storeUuid)
                .purchaseDateFrom(purchaseDateFrom)
                .purchaseDateTo(purchaseDateTo)
                .page(page)
                .size(size)
                .build();
            return purchaseItemService.getPurchaseItemRecordList(param);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list-types")
    public List<String> getItemTypeList() {
        try {
            return purchaseItemService.getAllItemTypes();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list-units")
    public List<String> getItemUnitList() {
        try {
            return purchaseItemService.getAllItemUnits();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list-categories")
    public List<String> getItemCategoryList() {
        try {
            return purchaseItemService.getAllItemCategories();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list-brands")
    public List<String> getItemBrandList() {
        try {
            return purchaseItemService.getAllItemBrands();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
