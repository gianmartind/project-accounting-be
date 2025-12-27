package com.gmd.project_accounting_be.modules.project.dtos.request;

import java.time.LocalDate;

import com.gmd.project_accounting_be.core.dtos.BaseGetListRequestDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class GetProjectRequestDTO extends BaseGetListRequestDTO{
    public String name;
    public String address;
    public LocalDate startDateFrom;
    public LocalDate startDateTo;
    public LocalDate endDateFrom;
    public LocalDate endDateTo;
    public Boolean completed;
}
