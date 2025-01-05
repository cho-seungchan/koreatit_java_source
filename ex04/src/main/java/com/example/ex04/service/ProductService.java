package com.example.ex04.service;

import java.util.List;

import com.example.ex04.dto.ProductDto;

public interface ProductService {

	List<ProductDto> getProductList();

	void postProductRegist(ProductDto product);

	ProductDto getProduct(long productId);
	
}
