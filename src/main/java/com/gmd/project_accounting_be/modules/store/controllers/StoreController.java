package com.gmd.project_accounting_be.modules.store.controllers;

import com.gmd.project_accounting_be.core.constants.BaseErrorMessages;
import com.gmd.project_accounting_be.modules.store.dto.request.GetStoreRequestDTO;
import com.gmd.project_accounting_be.modules.store.dto.response.StoreListRecordResponseDTO;
import com.gmd.project_accounting_be.modules.store.dto.response.projections.StoreListRecordSimpleDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gmd.project_accounting_be.modules.store.services.StoreService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, BaseErrorMessages.GENERAL_ERROR);
        }
    }

    @GetMapping("/list")
    public Page<StoreListRecordResponseDTO> getStoreList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) String sort
    ) {
        try {
            GetStoreRequestDTO param = GetStoreRequestDTO.builder()
                    .name(name)
                    .address(address)
                    .page(page)
                    .size(size)
                    .sort(sort)
                    .build();
            return storeService.getAllStoreListRecord(param);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, BaseErrorMessages.GENERAL_ERROR);
        }
    }
}
