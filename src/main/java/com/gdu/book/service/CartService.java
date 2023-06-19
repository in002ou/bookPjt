package com.gdu.book.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.gdu.book.domain.CartDTO;


public interface CartService {
	public void getCartList(HttpServletRequest request, Model model);
	public int addCart(HttpServletRequest request);
	public void removeCart(HttpServletRequest request, HttpServletResponse response);
	public void getcartDetail(HttpServletRequest request, Model model);
	public Map<String, Object> cartUp(HttpServletRequest request);
}
