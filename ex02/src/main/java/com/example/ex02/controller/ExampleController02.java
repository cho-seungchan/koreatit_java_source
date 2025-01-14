package com.example.ex02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ex02.domain.MemberVO;

@Controller
@RequestMapping("/ex02")
public class ExampleController02 {

	@GetMapping("/ex01")
	public String getEx01() {
		return "ex02/ex01/ex01";
	}
	
	@GetMapping("/ex02")
	public String getEx02(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		return "/ex02/ex02/ex02";
	}
	
	@GetMapping("/ex03")
	public String getEx03(MemberVO member, Model model) {
		model.addAttribute("member",member);
		return "ex02/ex03";
	}
	
	@GetMapping("/ex04")
	public void getEx04(@ModelAttribute("member") MemberVO member, @ModelAttribute("gender") String gender) {
		
	}
}
