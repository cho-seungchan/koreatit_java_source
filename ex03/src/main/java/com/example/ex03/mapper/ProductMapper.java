package com.example.ex03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.ex03.dto.ProductDto;

@Mapper
public interface ProductMapper {

	List<ProductDto> getProductList();

	void postProductRegister(ProductDto product);

	void reduceProductStock(@Param("productId") long productId, @Param("orderCount") int orderCount);

	boolean checkProductStock(@Param("productId") long productId, @Param("orderCount") int orderCount);


}
