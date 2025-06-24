package vn.nmd.microservice.order_service.service;

import org.apache.coyote.BadRequestException;

import vn.nmd.microservice.order_service.dto.OrderRequest;
import vn.nmd.microservice.order_service.entity.Order;

public interface IOrderService {
	public Order placeOrder(OrderRequest orderRequest) throws BadRequestException;
}
