package vn.nmd.microservice.inventory_service.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.nmd.microservice.inventory_service.repository.InventoryRepository;
import vn.nmd.microservice.inventory_service.service.IInventoryService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InventoryServiceImpl implements IInventoryService{
	
	private final InventoryRepository inventoryRepository;
	
	@Override
	public boolean isInStock(String skuCode, Integer quantityInput) {
		// find an inventory for a given skuCode where quantity >= quantityInput
		return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantityInput);
	}
}
