package vn.nmd.microservice.product_service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.nmd.microservice.product_service.dto.ProductRequest;
import vn.nmd.microservice.product_service.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductRest {
	
	@Autowired
	private IProductService productService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
		try {
			return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllData() {
		try {
			return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
