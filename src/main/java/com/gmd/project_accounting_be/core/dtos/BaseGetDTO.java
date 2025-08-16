package com.gmd.project_accounting_be.core.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BaseGetDTO {
    public Integer page;
    public Integer size;
}
