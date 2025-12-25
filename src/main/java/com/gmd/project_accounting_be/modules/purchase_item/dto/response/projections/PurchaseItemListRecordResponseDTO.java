package com.gmd.project_accounting_be.modules.purchase_item.dto.response.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PurchaseItemListRecordResponseDTO {
    String getName();
    String getType();
    Integer getAmount();
    String getUnit();
    BigDecimal getUnitPrice();
    BigDecimal getTotalPrice();
    String getBrand();
    String getCategory();
    String getProjectName();
    String getProjectUuid();
    String getStoreName();
    String getStoreUuid();
    LocalDate getPurchaseDate();

} 
