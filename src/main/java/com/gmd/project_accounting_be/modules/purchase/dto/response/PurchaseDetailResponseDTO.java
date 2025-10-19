package com.gmd.project_accounting_be.modules.purchase.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.gmd.project_accounting_be.modules.purchase.entities.PurchaseItem;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PurchaseDetailResponseDTO {
    private String uuid;
    private String storeName;
    private String projectUuid;
    private LocalDate purchaseDate;
    private String notes;
    private List<PurchaseItem> items;
}
