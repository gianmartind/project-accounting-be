package com.gmd.project_accounting_be.modules.project.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.gmd.project_accounting_be.modules.project.dtos.request.GetProjectRequestDTO;
import com.gmd.project_accounting_be.modules.project.dtos.response.projections.ProjectListRecordResponse;
import com.gmd.project_accounting_be.modules.project.entities.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String>, JpaSpecificationExecutor<Project> {
    @NonNull
    Optional<Project> findById(@NonNull String id);

    @Query(value = """
            SELECT pj.uuid as uuid, pj.name as name, pj.owner as owner, pj.city as city,
                   pj.address as address, pj.start_date as startDate, pj.end_date as endDate, pj.notes as notes
            FROM project pj
            WHERE (CAST(:#{#filter.name} AS VARCHAR) IS NULL OR LOWER(pj.name) LIKE LOWER(CAST(:#{#filter.name} AS VARCHAR)))
                AND (CAST(:#{#filter.owner} AS VARCHAR) IS NULL OR LOWER(pj.owner) LIKE LOWER(CAST(:#{#filter.owner} AS VARCHAR)))
                AND (CAST(:#{#filter.city} AS VARCHAR) IS NULL OR LOWER(pj.city) LIKE LOWER(CAST(:#{#filter.city} AS VARCHAR)))
                AND (CAST(:#{#filter.address} AS VARCHAR) IS NULL OR LOWER(pj.address) LIKE LOWER(CAST(:#{#filter.address} AS VARCHAR)))
                AND (CAST(:#{#filter.startDateFrom} AS DATE) IS NULL OR pj.start_date >= CAST(:#{#filter.startDateFrom} AS DATE))
                AND (CAST(:#{#filter.startDateTo} AS DATE) IS NULL OR pj.start_date <= CAST(:#{#filter.startDateTo} AS DATE))
                AND (CAST(:#{#filter.endDateFrom} AS DATE) IS NULL OR pj.end_date >= CAST(:#{#filter.endDateFrom} AS DATE))
                AND (CAST(:#{#filter.endDateTo} AS DATE) IS NULL OR pj.end_date <= CAST(:#{#filter.endDateTo} AS DATE))
                AND (CAST(:#{#filter.completed} AS BOOLEAN) IS NULL OR (CASE WHEN CAST(:#{#filter.completed} AS BOOLEAN) = true THEN pj.end_date IS NOT NULL ELSE pj.end_date IS NULL END))
            """, nativeQuery = true
        )
    Page<ProjectListRecordResponse> findAllProjectListRecord(@Param("filter") GetProjectRequestDTO filter, Pageable pageable);
}
