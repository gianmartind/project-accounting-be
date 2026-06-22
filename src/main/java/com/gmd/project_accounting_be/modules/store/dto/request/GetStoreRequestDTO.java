package com.gmd.project_accounting_be.modules.store.dto.request;

import com.gmd.project_accounting_be.core.dtos.BaseGetListRequestDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class GetStoreRequestDTO extends BaseGetListRequestDTO {
    public String name;
    public String address;
}
