package com.gmd.project_accounting_be.modules.purchase.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.gmd.project_accounting_be.modules.purchase.entities.PurchaseItem;


@Repository
public interface PurchaseItemRepository extends CrudRepository<PurchaseItem, String>, JpaSpecificationExecutor<PurchaseItem> {
    @NonNull
    Optional<PurchaseItem> findById(@NonNull String id);

    List<PurchaseItem> findAllByPurchaseUuid(String purchaseUuid);

    @Query(value = "SELECT DISTINCT type FROM purchase_item", nativeQuery = true)
    List<String> findAllDistinctTypes();
}
