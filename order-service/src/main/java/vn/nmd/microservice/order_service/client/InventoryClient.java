package vn.nmd.microservice.order_service.client;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

public interface InventoryClient {

	Logger logger = org.slf4j.LoggerFactory.getLogger(InventoryClient.class);

	@GetExchange("/api/inventory")
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallBackMethod")
	@Retry(name = "inventory")
	boolean checkInStock(@RequestParam("skuCode") String skuCode, @RequestParam("quantity") Integer quantity);

	default boolean fallBackMethod(String skuCode, Integer quantity, Throwable throwable) {
		logger.error(
				"Can't get information for cheking inventory for skuCode: {}, quantity: {}. Fallback method invoked.",
				skuCode, quantity, throwable);
		return false; // Fallback logic: assume out of stock
	}
}
