package com.example.ex04.service;

import java.util.List;

import javax.naming.InsufficientResourcesException;

import com.example.ex04.dto.OrderDto;
import com.example.ex04.dto.OrderListDto;

public interface OrderService {

	void postOrderDone(OrderDto order) throws InsufficientResourcesException;

	List<OrderListDto> getOrderListSort(String sort);
}
