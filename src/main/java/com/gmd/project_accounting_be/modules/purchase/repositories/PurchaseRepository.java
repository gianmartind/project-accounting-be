package com.gmd.project_accounting_be.modules.purchase.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.gmd.project_accounting_be.modules.purchase.entities.Purchase;


@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, String>, JpaSpecificationExecutor<Purchase> {
    @NonNull
    Optional<Purchase> findById(@NonNull String id);
}
