package com.example.ex00.dependency.qualify;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {

	@Override
	public int getSceeen() {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "laptop 입니다";
	}

}
