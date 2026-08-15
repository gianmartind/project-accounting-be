package com.gmd.project_accounting_be.modules.store.repositories;

import java.util.List;
import java.util.Optional;

import com.gmd.project_accounting_be.modules.store.dto.request.GetStoreRequestDTO;
import com.gmd.project_accounting_be.modules.store.dto.response.projections.StoreTagDTO;
import com.gmd.project_accounting_be.modules.store.dto.response.projections.StoreListRecordSimpleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gmd.project_accounting_be.modules.store.entities.Store;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends CrudRepository<Store, String> {

    @Query(value = "SELECT name FROM store", nativeQuery = true)
    List<String> getAllStoreNames();

    @Query(value = "SELECT * FROM store WHERE lower(name) = lower(?1)", nativeQuery = true)
    Optional<Store> findByName(String name);

    @Query(value = """
            SELECT s.uuid as uuid, s.name as name, s.address as address
            FROM store s
            WHERE (CAST(:#{#filter.name} AS VARCHAR) IS NULL OR LOWER(s.name) LIKE LOWER(CAST(:#{#filter.name} AS VARCHAR)))
                AND (CAST(:#{#filter.address} AS VARCHAR) IS NULL OR LOWER(s.address) LIKE LOWER(CAST(:#{#filter.address} AS VARCHAR)))
            """, nativeQuery = true
    )
    Page<StoreListRecordSimpleDTO> findAllStoreListRecord(@Param("filter") GetStoreRequestDTO filter, Pageable pageable);

    @Query(value = """
            WITH selected_store_has_tag AS (
                SELECT store_uuid, store_tag_uuid
                FROM store_has_tag sht
                WHERE sht.store_uuid in ?1
            ) 
            SELECT ssht.store_uuid as storeUuid, st.tag as tag
            FROM selected_store_has_tag ssht JOIN store_tag st ON ssht.store_tag_uuid = st.uuid
            """, nativeQuery = true
    )
    List<StoreTagDTO> getStoreTagList(List<String> storeUuidList);
}
