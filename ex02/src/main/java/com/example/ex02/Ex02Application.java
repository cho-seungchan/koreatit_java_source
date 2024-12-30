package com.example.ex02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.ex02.mapper")
public class Ex02Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex02Application.class, args);
	}

}
