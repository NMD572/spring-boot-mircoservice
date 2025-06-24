package vn.nmd.microservice.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.nmd.microservice.inventory_service.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);

}
