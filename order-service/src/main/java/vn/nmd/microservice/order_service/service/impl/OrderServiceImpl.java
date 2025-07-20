package vn.nmd.microservice.order_service.service.impl;

import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.nmd.microservice.order_service.client.InventoryClient;
import vn.nmd.microservice.order_service.dto.OrderRequest;
import vn.nmd.microservice.order_service.entity.Order;
import vn.nmd.microservice.order_service.repository.OrderRepository;
import vn.nmd.microservice.order_service.service.IOrderService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements IOrderService {

	private final OrderRepository orderRepository;
	private final InventoryClient inventoryClient;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Order placeOrder(OrderRequest orderRequest) throws BadRequestException {

		// For integration test, we can use Mockito or WireMock to simulate the response
		// of inventory service (because with integration test, we don't want to call
		// another service which may charge money for unnecessary call)

		Boolean isInStock = inventoryClient.checkInStock(orderRequest.skuCode(), orderRequest.quantity());
		if (isInStock) {
			// map order request to order object
			Order newOrder = new Order();
			newOrder.setOrderNumber(UUID.randomUUID().toString());
			newOrder.setSkuCode(orderRequest.skuCode());
			newOrder.setQuantity(orderRequest.quantity());
			newOrder.setPrice(orderRequest.price());
			// save to order repository
			orderRepository.save(newOrder);
			// TODO: decrease the number of quantity in inventory

			return newOrder;
		} else {
			throw new BadRequestException("Product with skuCode: " + orderRequest.skuCode() + " isn't in stock");
		}

	}

}
