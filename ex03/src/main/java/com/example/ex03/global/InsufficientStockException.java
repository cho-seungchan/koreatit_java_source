package com.example.ex03.global;

public class InsufficientStockException extends RuntimeException{

	public InsufficientStockException(String message) {
		super(message);
	}
	
}
