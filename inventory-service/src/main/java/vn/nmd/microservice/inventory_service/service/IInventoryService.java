package vn.nmd.microservice.inventory_service.service;

public interface IInventoryService {

	public boolean isInStock(String skuCode, Integer quantityInput);
}
