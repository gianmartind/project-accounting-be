package com.gmd.project_accounting_be.modules.project.dtos;

import java.time.LocalDate;

import com.gmd.project_accounting_be.core.dtos.BaseGetDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class GetProjectDTO extends BaseGetDTO{
    public String name;
    public String address;
    public LocalDate startDate;
    public LocalDate endDate;
}
