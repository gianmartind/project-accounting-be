package com.gmd.project_accounting_be.modules.purchase.dto.request;

import java.time.LocalDate;

import com.gmd.project_accounting_be.core.dtos.BaseGetListRequestDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class GetPurchaseListRecordRequestDTO extends BaseGetListRequestDTO{
    public String projectName;
    public String projectUuid;
    public String storeName;
    public String storeUuid;
    public LocalDate purchaseDate;
}
