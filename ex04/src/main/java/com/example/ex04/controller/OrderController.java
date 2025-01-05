package com.example.ex04.controller;

import java.util.List;

import javax.naming.InsufficientResourcesException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ex04.dto.OrderDto;
import com.example.ex04.dto.OrderListDto;
import com.example.ex04.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
	private final OrderService orderService;
	
	@PostMapping("/write")
	@Transactional(rollbackFor = InsufficientResourcesException.class)
	public void postOrderWrite(@RequestBody OrderDto order, RedirectAttributes redirectAttributes) {
		try {
			orderService.postOrderDone(order);
//			정상 메세지 보내기
			redirectAttributes.addFlashAttribute("successMessage", "주문이 정상적으로 처리되었습니다.");
//			입력값을 다시 보여주기 위해 보내기. 
			redirectAttributes.addFlashAttribute("productId", order.getProductId());
	        redirectAttributes.addFlashAttribute("orderPrice", order.getOrderPrice());
	        redirectAttributes.addFlashAttribute("orderCount", order.getOrderCount());
		} catch (InsufficientResourcesException e) {
//			오류 메세지 보내기
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
	}
	
	@GetMapping("/list/{sort}")
	@Transactional
	public List<OrderListDto> getOrderListSort(@PathVariable("sort") String sort){
		if (sort == null) {
			sort = "recent";
		}
		List<OrderListDto> list = orderService.getOrderListSort(sort);
		return list;
	}
}
