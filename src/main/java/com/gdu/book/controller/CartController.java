package com.gdu.book.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String addcart(HttpServletRequest request, RedirectAttributes redirectAttributes,Model model) {
		redirectAttributes.addFlashAttribute("addResult", cartService.addCart(request, model));
		
		return "redirect:/cart/cartList.do?userNo=" + request.getParameter("userNo"); 
	}
  
  
  
  

}
