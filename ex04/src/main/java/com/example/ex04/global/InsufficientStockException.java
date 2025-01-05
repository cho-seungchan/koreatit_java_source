package com.example.ex04.global;

public class InsufficientStockException extends RuntimeException{

	public InsufficientStockException(String message) {
		super(message);
	}
	
}
