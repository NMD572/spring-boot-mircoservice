package vn.nmd.microservice.inventory_service.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.nmd.microservice.inventory_service.service.IInventoryService;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryRest {
	
	private final IInventoryService inventoryService;
	
	@GetMapping()
	public ResponseEntity<?> checkInStock(@RequestParam("skuCode") String skuCode, @RequestParam("quantity") Integer quantity) {
		try {
			return new ResponseEntity<>(inventoryService.isInStock(skuCode, quantity), HttpStatus.CREATED);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
