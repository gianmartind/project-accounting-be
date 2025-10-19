package com.gmd.project_accounting_be.modules.purchase.dto.response.projections;

import java.time.LocalDate;
import java.math.BigDecimal;

public interface PurchaseListRecordResponseDTO {
    String getUuid();
    String getProjectName();
    String getProjectUuid();
    String getStoreName();
    String getStoreUuid();
    LocalDate getPurchaseDate();
    BigDecimal getTotalPrice();
}