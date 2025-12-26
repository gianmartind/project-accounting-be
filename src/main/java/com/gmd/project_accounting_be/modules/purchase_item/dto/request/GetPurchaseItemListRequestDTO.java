package com.gmd.project_accounting_be.modules.purchase_item.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.gmd.project_accounting_be.core.dtos.BaseGetListRequestDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class GetPurchaseItemListRequestDTO extends BaseGetListRequestDTO{
    public String name;
    public String type;
    public String unit;
    public String brand;
    public String category;
    public String projectName;
    public String projectUuid;
    public String storeName;
    public String storeUuid;
    public LocalDate purchaseDateFrom;
    public LocalDate purchaseDateTo;
    public Integer amountMin;
    public Integer amountMax;
    public BigDecimal priceMin;
    public BigDecimal priceMax;
    public BigDecimal totalPriceMin;
    public BigDecimal totalPriceMax;
}
