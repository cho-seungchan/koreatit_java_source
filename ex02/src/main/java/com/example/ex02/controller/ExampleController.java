package com.example.ex02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ex02.domain.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ex")
@ResponseBody /* 콘솔에 로그를 찍기위해 설정 */
@Slf4j

//  콘솔에 로그를 찍기위한 controller
public class ExampleController {

	@GetMapping("/get01")
//	@RequestMapping(value = "/get", method=RequestMethod.GET)
	public void getEx01() {
		log.info("========== getEx01() ===============");
	}
	
	@GetMapping("/get02")
	public void getEx02(@RequestParam("id") String name, @RequestParam("age") int age) {
		log.info("id ::  "+name+"  age :: "+age);
	}
	
	@GetMapping("/get03")
	public void getEx03(MemberVO member) {
		log.info("member :: "+member.toString());
	}
	
	@GetMapping("/get04")
	public void getEx04(MemberVO member, @RequestParam("name") String name) {
		log.info("member  "+member.toString());
		log.info("name    "+name);
	}
	
	
}
