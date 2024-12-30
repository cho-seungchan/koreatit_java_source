package com.example.ex00.dependency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ChefTest {
	@Autowired
	Restaurant restaurant;
	
	@Test
	public void chefTest() {
		log.info("테스트입니다  "+restaurant.getChef().toString());
	}
	
}
