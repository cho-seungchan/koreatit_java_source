package com.example.ex04.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ex04.dto.ProductDto;
import com.example.ex04.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	@GetMapping("/list")
	public String getProductList(Model model){
		List<ProductDto> products = productService.getProductList();
		model.addAttribute("products", products);
		model.addAttribute("productForm", new ProductDto());
		return "list";
	}
	
	@PostMapping("/new")
	@ResponseBody
	public void postProductRegist(@RequestBody ProductDto product) {
		productService.postProductRegist(product);
	}
	
	@GetMapping("/{productId}")
	@ResponseBody
	public ProductDto getProduct(@PathVariable("productId") long productId) {
		return productService.getProduct(productId);
	}
	
}
