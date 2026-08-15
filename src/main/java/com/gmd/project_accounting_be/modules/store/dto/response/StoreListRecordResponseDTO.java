package com.gmd.project_accounting_be.modules.store.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StoreListRecordResponseDTO {
    private String uuid;
    private String name;
    private String address;
    private List<String> tags;
}
