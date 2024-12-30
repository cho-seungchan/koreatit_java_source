package com.example.ex00.dependency.qualify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ex00.dependency.ComputerInto;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class QualifierTest {
	@Autowired
	@Qualifier("desktop")
	Computer computer;
	
	@Test
	public void aualityTest() {
		log.info("테스트입니다  "+computer.toString());
		log.info(computer.getClass().getName());
	}
}
