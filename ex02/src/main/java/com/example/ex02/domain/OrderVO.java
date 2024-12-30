package com.example.ex02.domain;

import lombok.Data;

@Data
public class OrderVO {
	
	private long orderId;
	private long productId;
	private long orderCount;
	private String date;
}
