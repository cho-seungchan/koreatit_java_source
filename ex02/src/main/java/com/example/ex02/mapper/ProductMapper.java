package com.example.ex02.mapper;

import java.util.List;  

import org.apache.ibatis.annotations.Mapper;

import com.example.ex02.domain.ProductVO;

@Mapper
public interface ProductMapper {
	
	void postResister(ProductVO product);
	
	List<ProductVO> getList();

}

