package com.example.ex03.service;

import java.util.List;

import javax.naming.InsufficientResourcesException;

import com.example.ex03.dto.OrderListDto;

public interface OrderService {

	void postOrderDone(long productId, long orderPrice, int orderCount) throws InsufficientResourcesException;

	List<OrderListDto> getOrderList(String sort);
}
