package com.example.ex02.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MapperTest {

	@Autowired
	private TimeMapper tm;
	
	@Test
	public void timeMapperTest() {
		log.info("timeMapper test !!!!!");
		log.info("timeMapper test !!!!!");
		log.info("timeMapper test !!!!!");
		log.info("timeMapper test !!!!!");
		log.info(tm.getTime());
	}
}
