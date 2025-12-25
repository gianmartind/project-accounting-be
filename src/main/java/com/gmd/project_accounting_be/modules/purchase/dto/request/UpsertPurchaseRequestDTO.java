package com.gmd.project_accounting_be.modules.purchase.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.gmd.project_accounting_be.modules.purchase_item.entities.PurchaseItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpsertPurchaseRequestDTO {
    @NotBlank
    private String storeName;
    
    @NotBlank
    private String projectUuid;

    @NotNull
    private LocalDate purchaseDate;

    @Size(max = 255)
    private String notes;

    @NotNull
    @Size(min = 1)  
    List<PurchaseItem> items;
}
