package com.gmd.project_accounting_be.core.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class CommonUtil {
    public static Sort generateSort(String sort) {
        try {
            if (sort == null || sort.isBlank())
                return Sort.unsorted();
            String[] sortParams = sort.split(":");
            String sortField = sortParams[0];
            Direction sortDirection = sortParams.length > 1
                    && sortParams[1].equalsIgnoreCase("descend")
                            ? Direction.DESC
                            : Direction.ASC;
            return Sort.by(sortDirection, sortField);
        } catch (Exception e) {
            return Sort.unsorted();
        }

    }
}
