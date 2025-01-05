package com.example.ex04.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ex04.dto.ProductDto;
import com.example.ex04.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductMapper productMapper;

	@Override
	public List<ProductDto> getProductList() {
		List<ProductDto> list = productMapper.getProductList();
		
		return list;
	}

	@Override
	public void postProductRegist(ProductDto product) {
		productMapper.postProductRegist(product);
	}

	@Override
	public ProductDto getProduct(long productId) {
		return productMapper.getProduct(productId);
	}
	

}
