package com.gmd.project_accounting_be.modules.purchase.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PurchaseAvailableFilterOptionsResponseDTO {
    List<String> projectOptions;
    List<String> storeOptions;
}
