package com.gmd.project_accounting_be.modules.purchase_item.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gmd.project_accounting_be.modules.purchase_item.dto.request.GetPurchaseItemListRequestDTO;
import com.gmd.project_accounting_be.modules.purchase_item.dto.response.projections.PurchaseItemListRecordResponseDTO;
import com.gmd.project_accounting_be.modules.purchase_item.entities.PurchaseItem;


@Repository
public interface PurchaseItemRepository extends CrudRepository<PurchaseItem, String>, JpaSpecificationExecutor<PurchaseItem> {
    List<PurchaseItem> findAllByPurchaseUuid(String purchaseUuid);

    @Query(value = "SELECT DISTINCT type FROM purchase_item", nativeQuery = true)
    List<String> findAllDistinctTypes();

    @Query(value = "SELECT DISTINCT unit FROM purchase_item", nativeQuery = true)
    List<String> findAllDistinctUnits();

    @Query(value = "SELECT DISTINCT category FROM purchase_item", nativeQuery = true)
    List<String> findAllDistinctCategory();

    @Query(value = "SELECT DISTINCT brand FROM purchase_item", nativeQuery = true)
    List<String> findAllDistinctBrand();

    @Query(value = """
            SELECT pc.purchase_date as purchaseDate, st.name as storeName, pj.name as projectName, 
                    pi.name, pi.type, pi.brand, pi.category, pi.amount, pi.unit, 
                    pi.price, pi.amount * pi.price as totalPrice 
            FROM purchase_item pi
                JOIN purchase pc ON pi.purchase_uuid = pc.uuid
                JOIN store st ON pc.store_uuid = st.uuid
                JOIN project pj ON pc.project_uuid = pj.uuid
            WHERE (CAST(:#{#filter.name} AS VARCHAR) IS NULL OR LOWER(pi.name) LIKE LOWER(CAST(:#{#filter.name} AS VARCHAR)))
                AND (CAST(:#{#filter.type} AS VARCHAR) IS NULL OR LOWER(pi.type) LIKE LOWER(CAST(:#{#filter.type} AS VARCHAR)))
                AND (CAST(:#{#filter.unit} AS VARCHAR) IS NULL OR LOWER(pi.unit) LIKE LOWER(CAST(:#{#filter.unit} AS VARCHAR)))
                AND (CAST(:#{#filter.brand} AS VARCHAR) IS NULL OR LOWER(pi.brand) LIKE LOWER(CAST(:#{#filter.brand} AS VARCHAR)))
                AND (CAST(:#{#filter.category} AS VARCHAR) IS NULL OR LOWER(pi.category) LIKE LOWER(CAST(:#{#filter.category} AS VARCHAR)))
                AND (CAST(:#{#filter.projectName} AS VARCHAR) IS NULL OR LOWER(pj.name) LIKE LOWER(CAST(:#{#filter.projectName} AS VARCHAR)))
                AND (CAST(:#{#filter.projectUuid} AS VARCHAR) IS NULL OR pj.uuid = CAST(:#{#filter.projectUuid} AS VARCHAR))
                AND (CAST(:#{#filter.storeName} AS VARCHAR) IS NULL OR LOWER(s.name) LIKE LOWER(CAST(:#{#filter.storeName} AS VARCHAR)))
                AND (CAST(:#{#filter.storeUuid} AS VARCHAR) IS NULL OR s.uuid = CAST(:#{#filter.storeUuid} AS VARCHAR))
                AND (CAST(:#{#filter.purchaseDateFrom} AS DATE) IS NULL OR p.purchase_date >= CAST(:#{#filter.purchaseDateFrom} AS DATE))
                AND (CAST(:#{#filter.purchaseDateTo} AS DATE) IS NULL OR p.purchase_date <= CAST(:#{#filter.purchaseDateTo} AS DATE))
            """, nativeQuery = true)
    Page<PurchaseItemListRecordResponseDTO> findAllPurchaseItemRecord(
            @Param("filter") GetPurchaseItemListRequestDTO filter,
            Pageable pageable);
}
