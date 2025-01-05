package com.example.ex04.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.ex04.dto.OrderDto;
import com.example.ex04.dto.ProductDto;

@Mapper
public interface ProductMapper {

	List<ProductDto> getProductList();

	void postProductRegist(ProductDto product);

	void reduceProductStock(OrderDto order);

	boolean checkProductStock(OrderDto order);

	ProductDto getProduct(long productId);


}
