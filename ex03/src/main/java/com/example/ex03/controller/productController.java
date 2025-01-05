package com.example.ex03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ex03.dto.ProductDto;
import com.example.ex03.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class productController {

	private final ProductService productService;

	
	@GetMapping("/register")
	public String getProductRegister(Model model) {
		model.addAttribute("product", new ProductDto());
		return "/product/register";
	}
	
	@PostMapping("/register")
	@Transactional
	public String postProductRegister(@ModelAttribute("product") ProductDto product) {
		productService.postProductRegister(product);
		return "redirect:/product/list";
	}
	
	@GetMapping("/list")
	public String getProductList(Model model) {
////		이전 화면에서 입력한 값들을 성공/실해 메세지와 함께 보여주기 위해 보냄. 
//        model.addAttribute("productId", model.getAttribute("productId"));
//        model.addAttribute("orderPrice", model.getAttribute("orderPrice"));
//        model.addAttribute("orderCount", model.getAttribute("orderCount"));

		model.addAttribute("products", productService.getProductList());

		return "/product/list";
	}
}
