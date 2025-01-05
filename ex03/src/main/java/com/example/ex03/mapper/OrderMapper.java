package com.example.ex03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.ex03.dto.OrderListDto;

@Mapper
public interface OrderMapper {

	void postOrderDone(@Param("productId") long productId, @Param("orderPrice") long orderPrice, @Param("orderCount") int orderCount);

	List<OrderListDto> getOrderList(@Param("sort") String sort);

}
