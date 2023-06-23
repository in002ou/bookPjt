package com.gdu.book.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.book.service.CartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

	private final CartService cartService; 

	@GetMapping("/cartList.do")
	public String cartList(HttpServletRequest request, Model model) {
		cartService.getCartList(request, model);
		
		return "cart/cartlist";
	}
	
	@PostMapping("/addCart.do")
	public String addcart(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("addResult", cartService.addCart(request));
		
		return "redirect:/cart/cartList.do?userNo=" + request.getParameter("userNo"); 
	}
	
	@PostMapping("/removeCart.do")
	public void removeCart(HttpServletRequest request, HttpServletResponse response) {
		cartService.removeCart(request, response);
		
	}
	
	@GetMapping("/cartDetail.do")
	public String cartDetail(HttpServletRequest request, Model model) {
		cartService.getcartDetail(request, model);
		return "cart/cartdetail";
	}
  
	@ResponseBody
	@PostMapping(value="/countUp.do", produces="application/json")
	public Map<String, Object> cartUp(HttpServletRequest request) {
		
		return cartService.cartUp(request);
	}
	@ResponseBody
	@PostMapping(value="/countDown.do", produces="application/json")
	public Map<String, Object> cartDown(HttpServletRequest request) {
		
		return cartService.cartDown(request);
	}
  

}
