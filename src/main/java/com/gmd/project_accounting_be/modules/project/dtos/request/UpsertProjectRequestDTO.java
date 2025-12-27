package com.gmd.project_accounting_be.modules.project.dtos.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpsertProjectRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String owner;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @Size(max = 255)
    private String notes;
}
