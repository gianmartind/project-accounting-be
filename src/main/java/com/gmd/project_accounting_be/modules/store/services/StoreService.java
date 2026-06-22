package com.gmd.project_accounting_be.modules.store.services;

import java.util.List;

import com.gmd.project_accounting_be.core.utils.CommonUtil;
import com.gmd.project_accounting_be.modules.store.dto.request.GetStoreRequestDTO;
import com.gmd.project_accounting_be.modules.store.dto.response.StoreListRecordResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<StoreListRecordResponse> getAllStoreListRecord(GetStoreRequestDTO param) {
        Sort sort = CommonUtil.generateSort(param.getSort());
        Pageable pageable = PageRequest.of(param.getPage(), param.getSize(), sort);
        return storeRepository.findAllStoreListRecord(param, pageable);
    }
}
