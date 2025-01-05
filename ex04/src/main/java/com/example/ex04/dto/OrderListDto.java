package com.example.ex04.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderListDto {
	
    private String productName;
    private long   productPrice;
    private long   orderId;
    private int    orderCount;
    private int    orderPrice;
    private long   orderAmount;
    private String orderDate;

}
