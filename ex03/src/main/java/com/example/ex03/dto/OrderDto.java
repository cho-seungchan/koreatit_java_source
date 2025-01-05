package com.example.ex03.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
	long    orderId;
	long    productId;
	int     orderPrice;
	int     orderCount;
	String  orderDate;
}
