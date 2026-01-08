package com.pabasara.microservice.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pabasara.microservice.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);

}
