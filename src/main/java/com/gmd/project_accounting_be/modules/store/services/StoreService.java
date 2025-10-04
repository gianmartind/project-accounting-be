package com.gmd.project_accounting_be.modules.store.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gmd.project_accounting_be.modules.store.repositories.StoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public List<String> getAllStoreNames() {
        try {
            return storeRepository.getAllStoreNames();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
