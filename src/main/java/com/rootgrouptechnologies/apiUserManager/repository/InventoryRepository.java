package com.rootgrouptechnologies.apiUserManager.repository;

import com.rootgrouptechnologies.apiUserManager.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findInventoryByPasswordAndQuantityAndLicenceTypeId(String password, Integer quantity, Integer id);
    Inventory findInventoryByPassword(String password);
}
