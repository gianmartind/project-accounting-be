package com.gmd.project_accounting_be.modules.store.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmd.project_accounting_be.modules.store.services.StoreService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/list-names")
    public List<String> getStoreNameList() {
        try {
            return storeService.getAllStoreNames();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
