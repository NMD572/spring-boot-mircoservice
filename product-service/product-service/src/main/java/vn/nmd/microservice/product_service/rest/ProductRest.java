package vn.nmd.microservice.product_service.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.nmd.microservice.product_service.dto.ProductRequest;

@RestController
@RequestMapping("/api/product")
public class ProductRest {
	
	@PostMapping
	public void createProduct(@RequestBody ProductRequest productRequest) {
		
	}
}
