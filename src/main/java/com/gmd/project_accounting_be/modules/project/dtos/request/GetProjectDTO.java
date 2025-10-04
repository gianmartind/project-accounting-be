package com.gmd.project_accounting_be.modules.project.dtos.request;

import java.time.LocalDate;

import com.gmd.project_accounting_be.core.dtos.BaseGetListDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class GetProjectDTO extends BaseGetListDTO{
    public String name;
    public String address;
    public LocalDate startDate;
    public LocalDate endDate;
}
