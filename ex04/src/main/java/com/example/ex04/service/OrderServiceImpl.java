package com.example.ex04.service;

import java.util.List;

import javax.naming.InsufficientResourcesException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ex04.dto.OrderDto;
import com.example.ex04.dto.OrderListDto;
import com.example.ex04.mapper.OrderMapper;
import com.example.ex04.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	private final OrderMapper orderMapper;
	private final ProductMapper productMapper;

	@Override
	@Transactional
	public void postOrderDone(OrderDto order) throws InsufficientResourcesException {

//		product 재고가 존재하는지 확인
		if (!productMapper.checkProductStock(order)) {
			throw new InsufficientResourcesException("재고가 부족합니다");
		};
		
//		order record 생성
		orderMapper.postOrderDone(order);
		
//		product 재고 차감
		productMapper.reduceProductStock(order);
		
	}

	@Override
	public List<OrderListDto> getOrderListSort(String sort) {
		
		List<OrderListDto> list = orderMapper.getOrderListSort(sort);
		
		return list;
	}	
	
	
	
	
}
