package com.gmd.project_accounting_be.modules.project.dtos;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateProjectDTO {
    private String name;
    private String address;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;
}
