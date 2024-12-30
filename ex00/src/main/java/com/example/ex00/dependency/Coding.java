package com.example.ex00.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Getter
public class Coding {

	private final ComputerInto computer;


//	@Autowired :: 생성자가 하나인 경우, @RequiredArgsConstructor가 선언된 경우 없어도 됨
//	public Coding(Computer computer) {   //  @RequiredArgsConstructor가 선언된 경우 생성자 없어도 됨
//		this.computer = computer;
//	}
	
}
