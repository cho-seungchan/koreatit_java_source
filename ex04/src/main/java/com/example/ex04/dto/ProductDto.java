package com.example.ex04.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

	private long   productId;
	private String productName;
	private long   productStock;
	private int    productPrice;
	private String registerDate;
	private String updateDate;
}
