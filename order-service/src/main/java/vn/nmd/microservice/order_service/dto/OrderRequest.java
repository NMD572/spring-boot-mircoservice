package vn.nmd.microservice.order_service.dto;

import java.math.BigDecimal;

public record OrderRequest(Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity,
		UserDetails userDetails) {

	public record UserDetails(String email, String firstName, String lastName) {
		// This record can be used to encapsulate user details
		// It can be extended with more fields if needed
	}
}
