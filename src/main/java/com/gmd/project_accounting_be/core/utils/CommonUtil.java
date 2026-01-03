package com.gmd.project_accounting_be.core.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;

public class CommonUtil {
    public static Sort generateSort(String sort) {
        try {
            if (sort == null || sort.isBlank())
                return Sort.unsorted();
            String[] sortParams = sort.split(":");
            String sortField = snakeCaseToCamelCase(sortParams[0]);
            Direction sortDirection = sortParams.length > 1
                    && sortParams[1].equalsIgnoreCase("descend")
                            ? Direction.DESC
                            : Direction.ASC;
            return JpaSort.unsafe(sortDirection, String.format("(%s)", sortField));
        } catch (Exception e) {
            return Sort.unsorted();
        }
    }

    public static String snakeCaseToCamelCase(String snake) {
        if (snake == null || snake.isEmpty()) {
            return "";
        }

        StringBuilder camel = new StringBuilder();
        boolean toUpper = false;

        for (char ch : snake.toCharArray()) {
            if (ch == '_') {
                toUpper = true; // Next character should be uppercase
            } else {
                if (toUpper) {
                    camel.append(Character.toUpperCase(ch));
                    toUpper = false;
                } else {
                    camel.append(Character.toLowerCase(ch));
                }
            }
        }

        return camel.toString();
    }
}
