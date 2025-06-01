package vn.nmd.microservice.product_service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.nmd.microservice.product_service.dto.ProductData;
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
	@Transactional(rollbackFor = Exception.class)
	public Product createProduct(ProductRequest request) {
		Product product = objectMapper.convertValue(request, Product.class);
		return productRepository.save(product);
	}

	@Override
	public List<ProductData> getAllProduct() {
		List<Product> listAllProduct = productRepository.findAll();
		List<ProductData> listFinalData = new ArrayList<>();
		for(Product prod : listAllProduct) {
			listFinalData.add(objectMapper.convertValue(prod, ProductData.class));
		}
		return listFinalData;
	}

}
