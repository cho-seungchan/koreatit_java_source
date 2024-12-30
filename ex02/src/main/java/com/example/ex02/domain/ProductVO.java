package com.example.ex02.domain;

import lombok.Data;

@Data
public class ProductVO {
	
	private long productId;
	private String productName;
	private long productStock;
	private long productPrice;
	private String registerDate;
	private String updateDate;
	
}
