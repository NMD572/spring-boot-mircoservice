package vn.nmd.microservice.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.nmd.microservice.order_service.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
