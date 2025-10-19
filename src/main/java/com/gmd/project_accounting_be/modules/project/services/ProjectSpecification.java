package com.gmd.project_accounting_be.modules.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import com.gmd.project_accounting_be.modules.project.dtos.request.GetProjectRequestDTO;
import com.gmd.project_accounting_be.modules.project.entities.Project;

public class ProjectSpecification {
    public static Specification<Project> getSpecification(GetProjectRequestDTO filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getName() != null && !filter.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), 
                    "%" + filter.getName().toLowerCase() + "%"
                ));
            }

            if (filter.getAddress() != null && !filter.getAddress().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("address")), 
                    "%" + filter.getAddress().toLowerCase() + "%"
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
