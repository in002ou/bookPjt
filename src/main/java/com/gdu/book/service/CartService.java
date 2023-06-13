package com.gdu.book.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.gdu.book.domain.CartDTO;

public interface CartService {
	public void getCartList(HttpServletRequest request, Model model);
	public int addCart(HttpServletRequest request, Model model);
}
