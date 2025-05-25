package vn.nmd.microservice.product_service.service;

import vn.nmd.microservice.product_service.dto.ProductRequest;
import vn.nmd.microservice.product_service.entity.Product;

public interface IProductService {
	Product createProduct(ProductRequest request);
}
