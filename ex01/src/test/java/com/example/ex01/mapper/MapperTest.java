package com.example.ex01.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ex01.domain.ProductVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MapperTest {

	@Autowired
	private TimeMapper timeMapper;
	
	@Test
	public void timeTest() {
		log.info("timeMapper test !!!!!");
		log.info("timeMapper test !!!!!");
		log.info("timeMapper test !!!!!");
		log.info("timeMapper test !!!!!");
		log.info(timeMapper.getTime());
	}
	
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	public void insertTest() {
		ProductVO pv = new ProductVO();
		pv.setProductName("galaxy");
		pv.setProductStock(10l);
		pv.setProductPrice(100000l);
		
		productMapper.insert(pv);
		
		log.info("insert completed !!!");
	}
	
	@Test
	public void selectTest() {
		ProductVO pv = productMapper.select(1l);
		
		log.info("select completed  "+pv.toString());
	}
	
	@Test
	public void selectAllTest(){
		List<ProductVO> productList = productMapper.selectAll();
		
		log.info("select completed  ");
		for (ProductVO pv : productList) {
			log.info(pv.toString());
		}
			
		assertThat(productMapper.selectAll().get(0).getProductName()).isEqualTo("park");
	}
	
	@Test
	public void updateTest() {
		ProductVO pv = productMapper.select(2l);
		log.info("selected row ::  "+pv.toString());
		pv.setProductName("park");
		pv.setProductStock(100l);
		pv.setProductPrice(300000);
		productMapper.update(pv);
		
		pv = productMapper.select(4l);
		log.info("selected row ::  "+pv.toString());
		pv.setProductName("choi");
		pv.setProductStock(500);
		pv.setProductPrice(500000l);
		productMapper.update(pv);
		
		selectAllTest();
	}
	
	@Test
	public void deleteTest() {
		ProductVO pv = productMapper.select(6l);
		log.info("selected row :: "+pv.toString());
		
		productMapper.delete(6l);
		selectAllTest();
	}
}
