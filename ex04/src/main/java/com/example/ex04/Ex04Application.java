package com.example.ex04;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.ex04.mapper")
@SpringBootApplication
public class Ex04Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex04Application.class, args);
	}

}
