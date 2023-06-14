package com.gdu.book.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;


public interface CartService {
	public void getCartList(HttpServletRequest request, Model model);
	public int addCart(HttpServletRequest request);
	public void removeCart(HttpServletRequest request, HttpServletResponse response);
	public void countDown(HttpServletRequest request, HttpServletResponse response);
}
