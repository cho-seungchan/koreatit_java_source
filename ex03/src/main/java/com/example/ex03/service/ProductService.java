package com.example.ex03.service;

import java.util.List;

import com.example.ex03.dto.ProductDto;

public interface ProductService {

	List<ProductDto> getProductList();

	void postProductRegister(ProductDto product);
	
}
