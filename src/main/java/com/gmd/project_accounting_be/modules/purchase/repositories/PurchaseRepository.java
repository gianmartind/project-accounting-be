package com.gmd.project_accounting_be.modules.purchase.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.gmd.project_accounting_be.modules.purchase.dto.request.GetPurchaseListRecordRequestDTO;
import com.gmd.project_accounting_be.modules.purchase.dto.response.projections.PurchaseListRecordResponseDTO;
import com.gmd.project_accounting_be.modules.purchase.entities.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, String>, JpaSpecificationExecutor<Purchase> {
    @NonNull
    Optional<Purchase> findById(@NonNull String id);

    @Query(value = """
            SELECT p.uuid as uuid, pj.name as projectName, pj.uuid as projectUuid, s.name as storeName, p.purchase_date as purchaseDate, sum(pi.price) as totalPrice
            FROM purchase p
                JOIN purchase_item pi ON p.uuid = pi.purchase_uuid
                JOIN project pj ON p.project_uuid = pj.uuid
                JOIN store s ON p.store_uuid = s.uuid
            WHERE (CAST(:#{#filter.projectName} AS VARCHAR) IS NULL OR LOWER(pj.name) LIKE LOWER(CONCAT('%', CAST(:#{#filter.projectName} AS VARCHAR), '%')))
            AND (CAST(:#{#filter.storeName} AS VARCHAR) IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', CAST(:#{#filter.storeName} AS VARCHAR), '%')))
            AND (CAST(:#{#filter.projectUuid} AS VARCHAR) IS NULL OR pj.uuid = CAST(:#{#filter.projectUuid} AS VARCHAR))
            AND (CAST(:#{#filter.storeUuid} AS VARCHAR) IS NULL OR s.uuid = CAST(:#{#filter.storeUuid} AS VARCHAR))
            GROUP BY p.uuid, pj.name, pj.uuid, s.name, s.uuid, p.purchase_date
            """, nativeQuery = true)
    Page<PurchaseListRecordResponseDTO> findAllPurchaseRecord(
            @Param("filter") GetPurchaseListRecordRequestDTO filter,
            Pageable pageable);
}
