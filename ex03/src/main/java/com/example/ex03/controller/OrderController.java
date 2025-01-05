package com.example.ex03.controller;

import javax.naming.InsufficientResourcesException;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ex03.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
	private final OrderService orderService;

	@PostMapping("/done")
	@Transactional(rollbackFor = InsufficientResourcesException.class)
	public String postOrderDone(@RequestParam("productId") long productId,
			@RequestParam("orderPrice") int orderPrice,
			@RequestParam("orderCount") int orderCount, RedirectAttributes redirectAttributes) {
		
		try {
			orderService.postOrderDone(productId, orderPrice, orderCount);
//			정상 메세지 보내기
			redirectAttributes.addFlashAttribute("successMessage", "주문이 정상적으로 처리되었습니다.");
//			입력값을 다시 보여주기 위해 보내기. 
			redirectAttributes.addFlashAttribute("productId", productId);
	        redirectAttributes.addFlashAttribute("orderPrice", orderPrice);
	        redirectAttributes.addFlashAttribute("orderCount", orderCount);
			return "redirect:/product/list";
			
		} catch (InsufficientResourcesException e) {
//			오류 메세지 보내기
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/product/list"; // 현재 화면을 그대로 유지
		}

	}
	
	@GetMapping("/list")
	public String getOrderList(@RequestParam(value="sort", defaultValue = "recent") String sort, Model model) {
		model.addAttribute("orders",orderService.getOrderList(sort));
		model.addAttribute("sort", sort);
		return "/order/list";
	}
}
