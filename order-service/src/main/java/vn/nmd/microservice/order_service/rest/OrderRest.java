package vn.nmd.microservice.order_service.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.nmd.microservice.order_service.dto.OrderRequest;
import vn.nmd.microservice.order_service.service.IOrderService;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderRest {
	
	private final IOrderService orderService;
	
	
	@PostMapping()
	public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
		try {
			return new ResponseEntity<>(orderService.placeOrder(orderRequest), HttpStatus.CREATED);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
