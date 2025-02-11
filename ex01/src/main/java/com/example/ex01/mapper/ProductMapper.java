package com.example.ex01.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.ex01.domain.ProductVO;

@Mapper
public interface ProductMapper {
//	상품 추가
	public void insert(ProductVO productVO);
//	상품 조회
	public ProductVO select(@Param("productId") Long productId);
//	상품 수정
	public void update(ProductVO productVO);
//	상품 삭제
	public void delete(@Param("productId") Long productId);
//	상품 전체조회
	public List<ProductVO> selectAll();
}
