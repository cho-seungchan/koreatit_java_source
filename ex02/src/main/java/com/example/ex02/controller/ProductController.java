package com.example.ex02.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ex02.domain.ProductVO;
import com.example.ex02.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductMapper productMapper;

	@GetMapping("list")
	public String getList(Model model) {
//		List<ProductVO> products = productMapper.getList();
//		model.addAttribute("products",products);
		model.addAttribute("products",productMapper.getList());
		
		return "product/product-list";
	}
	
	@GetMapping("register")
	public String getRegister(Model model) {
		model.addAttribute("product", new ProductVO());
		return "product/product-register";
	}
	
	@PostMapping("register")
	@Transactional
	public String postRegister(@ModelAttribute("product") ProductVO product) {
		productMapper.postResister(product);
		return "redirect:/product/list";
	}
}
