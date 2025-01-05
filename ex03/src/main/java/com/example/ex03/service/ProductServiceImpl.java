package com.example.ex03.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ex03.dto.ProductDto;
import com.example.ex03.mapper.ProductMapper;

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
	public void postProductRegister(ProductDto product) {
		productMapper.postProductRegister(product);
	}
	

}
