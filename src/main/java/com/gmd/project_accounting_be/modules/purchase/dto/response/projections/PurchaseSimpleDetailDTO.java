package com.gmd.project_accounting_be.modules.purchase.dto.response.projections;

import java.time.LocalDate;

public interface PurchaseSimpleDetailDTO {
    String getUuid();
    String getStoreName();
    String getProjectUuid();
    LocalDate getPurchaseDate();
    String getNotes();
}
