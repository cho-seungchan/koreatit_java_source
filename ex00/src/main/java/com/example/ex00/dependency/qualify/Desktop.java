package com.example.ex00.dependency.qualify;

import org.springframework.stereotype.Component;

@Component
public class Desktop implements Computer {

	@Override
	public int getSceeen() {
		// TODO Auto-generated method stub
		return 2000;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "desktop 입니다";
	}

}
