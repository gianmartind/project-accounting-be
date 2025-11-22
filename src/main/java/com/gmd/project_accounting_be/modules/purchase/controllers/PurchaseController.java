package com.gmd.project_accounting_be.modules.purchase.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gmd.project_accounting_be.modules.project.constants.ProjectErrorMessages;
import com.gmd.project_accounting_be.modules.purchase.dto.request.GetPurchaseListRecordRequestDTO;
import com.gmd.project_accounting_be.modules.purchase.dto.request.UpsertPurchaseRequestDTO;
import com.gmd.project_accounting_be.modules.purchase.dto.response.PurchaseAvailableFilterOptionsResponseDTO;
import com.gmd.project_accounting_be.modules.purchase.dto.response.PurchaseDetailResponseDTO;
import com.gmd.project_accounting_be.modules.purchase.dto.response.projections.PurchaseListRecordResponseDTO;
import com.gmd.project_accounting_be.modules.purchase.entities.Purchase;
import com.gmd.project_accounting_be.modules.purchase.services.PurchaseService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public Page<PurchaseListRecordResponseDTO> getPuchaseList(
            @RequestParam(name = "store_name", required = false) String storeName,
            @RequestParam(name = "store_uuid", required = false) String storeUuid,
            @RequestParam(name = "project_name", required = false) String projectName,
            @RequestParam(name = "project_uuid", required = false) String projectUuid,
            @RequestParam(name = "purchase_date", required = false) LocalDate purchaseDate,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        try {
            GetPurchaseListRecordRequestDTO param = GetPurchaseListRecordRequestDTO.builder()
                    .storeName(storeName)
                    .storeUuid(storeUuid)
                    .projectName(projectName)
                    .projectUuid(projectUuid)
                    .purchaseDate(purchaseDate)
                    .page(page)
                    .size(size)
                    .build();
            return purchaseService.getPurchaseRecordList(param);
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
    public UpsertPurchaseRequestDTO insertPurchase(@RequestBody UpsertPurchaseRequestDTO body) {
        try {
            return purchaseService.insert(body);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/detail/{uuid}")
    public PurchaseDetailResponseDTO getPurchaseDetail(@PathVariable String uuid) {
        try {
            return purchaseService.getDetail(uuid);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ProjectErrorMessages.NOT_FOUND);
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
    public PurchaseDetailResponseDTO updatePurchase(@PathVariable String uuid,
            @RequestBody UpsertPurchaseRequestDTO body) {
        try {
            return purchaseService.updateByUuid(uuid, body);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/available-filter-options")
    public PurchaseAvailableFilterOptionsResponseDTO getAvailableFilterOptions() {
        try {
            return purchaseService.getAvailableFilterOptions();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
