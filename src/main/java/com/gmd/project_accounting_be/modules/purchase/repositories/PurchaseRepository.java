package com.gmd.project_accounting_be.modules.purchase.repositories;

import java.util.List;
import java.util.Optional;

import com.gmd.project_accounting_be.modules.purchase.dto.response.projections.PurchaseSimpleDetailDTO;
import com.gmd.project_accounting_be.modules.purchase_item.entities.PurchaseItem;
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
public interface PurchaseRepository extends CrudRepository<Purchase, String>, JpaSpecificationExecutor<PurchaseItem> {
    @NonNull
    Optional<Purchase> findById(@NonNull String id);

    List<Purchase> findAllByProjectUuid(String projectUuid);

    @Query(value = """
            SELECT p.uuid as uuid, pj.name as projectName, pj.uuid as projectUuid, s.name as storeName, p.purchase_date as purchaseDate, sum(pi.price * pi.amount) as totalPrice
            FROM purchase p
                JOIN purchase_item pi ON p.uuid = pi.purchase_uuid
                JOIN project pj ON p.project_uuid = pj.uuid
                JOIN store s ON p.store_uuid = s.uuid
            WHERE (CAST(:#{#filter.projectName} AS VARCHAR) IS NULL OR LOWER(pj.name) LIKE LOWER(CAST(:#{#filter.projectName} AS VARCHAR)))
            AND (CAST(:#{#filter.storeName} AS VARCHAR) IS NULL OR LOWER(s.name) LIKE LOWER(CAST(:#{#filter.storeName} AS VARCHAR)))
            AND (CAST(:#{#filter.projectUuid} AS VARCHAR) IS NULL OR pj.uuid = CAST(:#{#filter.projectUuid} AS VARCHAR))
            AND (CAST(:#{#filter.storeUuid} AS VARCHAR) IS NULL OR s.uuid = CAST(:#{#filter.storeUuid} AS VARCHAR))
            AND (CAST(:#{#filter.purchaseDateFrom} AS DATE) IS NULL OR p.purchase_date >= CAST(:#{#filter.purchaseDateFrom} AS DATE))
            AND (CAST(:#{#filter.purchaseDateTo} AS DATE) IS NULL OR p.purchase_date <= CAST(:#{#filter.purchaseDateTo} AS DATE))
            GROUP BY p.uuid, pj.name, pj.uuid, s.name, s.uuid, p.purchase_date
            """, nativeQuery = true)
    Page<PurchaseListRecordResponseDTO> findAllPurchaseRecord(
            @Param("filter") GetPurchaseListRecordRequestDTO filter,
            Pageable pageable);

    @Query(value = """
            SELECT p.uuid as uuid, s.name as storeName, p.project_uuid as projectUuid,
                    p.purchase_date as purchaseDate, p.notes as notes
            FROM purchase p JOIN store s ON p.store_uuid = s.uuid
            WHERE p.uuid = ?1
            """, nativeQuery = true)
    PurchaseSimpleDetailDTO findSimpleDetailById(String id);
}
