package vn.nmd.microservice.order_service.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.nmd.microservice.order_service.dto.OrderRequest;
import vn.nmd.microservice.order_service.entity.Order;
import vn.nmd.microservice.order_service.repository.OrderRepository;
import vn.nmd.microservice.order_service.service.IOrderService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements IOrderService{

	private final OrderRepository orderRepository;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Order placeOrder(OrderRequest orderRequest) {
		// map order request to order object
		Order newOrder = new Order();
		newOrder.setOrderNumber(UUID.randomUUID().toString());
		newOrder.setSkuCode(orderRequest.skuCode());
		newOrder.setPrice(orderRequest.price());
		newOrder.setQuantity(orderRequest.quantity());
		// save to order repository
		return orderRepository.save(newOrder);
	}

}
