package com.gmd.project_accounting_be.modules.purchase.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmd.project_accounting_be.modules.purchase.dto.request.UpsertPurchaseDTO;
import com.gmd.project_accounting_be.modules.purchase.entities.Purchase;
import com.gmd.project_accounting_be.modules.purchase.entities.PurchaseItem;
import com.gmd.project_accounting_be.modules.purchase.services.PurchaseService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/list")
    public Page<Purchase> getPuchaseList(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list-types")
    public List<String> getItemTypeList() {
        try {
            return purchaseService.getAllItemTypes();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/insert")
    public UpsertPurchaseDTO insertPurchase(@RequestBody UpsertPurchaseDTO body) {
        try {
            return purchaseService.insert(body);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    

    @PostMapping("/delete/{uuid}")
    public Purchase deletePurchase(@PathVariable String uuid) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/update/{uuid}")
    public Purchase updatePurchase(@PathVariable String uuid, @RequestBody Object body) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/item/list/{uuid}")
    public List<PurchaseItem> getItemList(@PathVariable String uuid) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/item/insert/{uuid}")
    public PurchaseItem insertItem(@PathVariable String uuid, @RequestBody Object body) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/item/delete/{uuid}")
    public PurchaseItem deleteItem(@PathVariable String uuid) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
