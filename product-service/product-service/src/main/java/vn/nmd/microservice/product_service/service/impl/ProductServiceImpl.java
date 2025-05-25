package vn.nmd.microservice.product_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.nmd.microservice.product_service.dto.ProductRequest;
import vn.nmd.microservice.product_service.entity.Product;
import vn.nmd.microservice.product_service.repository.ProductRepository;
import vn.nmd.microservice.product_service.service.IProductService;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public Product createProduct(ProductRequest request) {
		Product product = objectMapper.convertValue(request, Product.class);
		return productRepository.save(product);
	}

}
