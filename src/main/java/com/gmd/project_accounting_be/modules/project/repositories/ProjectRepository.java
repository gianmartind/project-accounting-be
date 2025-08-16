package com.gmd.project_accounting_be.modules.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.gmd.project_accounting_be.modules.project.entities.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String>, JpaSpecificationExecutor<Project> {
    @NonNull
    Page<Project> findAll(@NonNull Pageable pageable);

    @NonNull
    Optional<Project> findById(@NonNull String id);
}
