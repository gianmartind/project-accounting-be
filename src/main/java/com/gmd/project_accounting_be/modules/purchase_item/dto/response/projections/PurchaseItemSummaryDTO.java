package com.gmd.project_accounting_be.modules.purchase_item.dto.response.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PurchaseItemSummaryDTO {
    BigDecimal getTotalPrice();
    LocalDate getFirstPurchaseDate();
    LocalDate getLastPurchaseDate();
}
