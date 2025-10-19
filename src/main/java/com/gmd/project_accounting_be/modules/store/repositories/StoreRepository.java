package com.gmd.project_accounting_be.modules.store.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gmd.project_accounting_be.modules.store.entities.Store;

public interface StoreRepository extends CrudRepository<Store, String>, JpaSpecificationExecutor<Store> {

    @Query(value = "SELECT name FROM store", nativeQuery = true)
    List<String> getAllStoreNames();

    @Query(value = "SELECT * FROM store WHERE lower(name) = lower(?1)", nativeQuery = true)
    Optional<Store> findByName(String name);
}
