package com.example.ex03.service;

import java.util.List;

import javax.naming.InsufficientResourcesException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ex03.dto.OrderListDto;
import com.example.ex03.mapper.OrderMapper;
import com.example.ex03.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	private final OrderMapper orderMapper;
	private final ProductMapper productMapper;

	@Override
	@Transactional
	public void postOrderDone(long productId, long orderPrice, int orderCount) throws InsufficientResourcesException {

//		product 재고가 존재하는지 확인
		if (!productMapper.checkProductStock(productId, orderCount)) {
			throw new InsufficientResourcesException("재고가 부족합니다");
		};
		
//		order record 생성
		orderMapper.postOrderDone(productId, orderPrice, orderCount);
		
//		product 재고 차감
		productMapper.reduceProductStock(productId,orderCount);
		
	}

	@Override
	public List<OrderListDto> getOrderList(String sort) {
		
		List<OrderListDto> list = orderMapper.getOrderList(sort);
		
		return list;
	}	
	
	
	
	
}
