package com.example.ex04.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.ex04.dto.OrderDto;
import com.example.ex04.dto.OrderListDto;

@Mapper
public interface OrderMapper {

	void postOrderDone(OrderDto order);

	List<OrderListDto> getOrderListSort(@Param("sort") String sort);

}
