package com.gmd.project_accounting_be.modules.purchase.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmd.project_accounting_be.modules.project.dtos.GetProjectDTO;
import com.gmd.project_accounting_be.modules.project.entities.Project;
import com.gmd.project_accounting_be.modules.purchase.entities.Purchase;
import com.gmd.project_accounting_be.modules.purchase.entities.PurchaseItem;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
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
