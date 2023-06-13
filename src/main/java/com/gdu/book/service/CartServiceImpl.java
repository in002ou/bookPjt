package com.gdu.book.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.book.domain.BookDTO;
import com.gdu.book.domain.CartDTO;
import com.gdu.book.domain.UserDTO;
import com.gdu.book.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	
	private final CartMapper cartMapper;

	@Override
	public int addCart(HttpServletRequest request, Model model) {
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int count = Integer.parseInt(request.getParameter("count"));
		BookDTO bookDTO = new BookDTO();
		UserDTO userDTO = new UserDTO();
		bookDTO.setBookNo(bookNo);
		userDTO.setUserNo(userNo);
		CartDTO cartDTO = new CartDTO();
		cartDTO.setBookDTO(bookDTO);
		cartDTO.setUserDTO(userDTO);
		cartDTO.setCount(count);
		
		int addCartResult = cartMapper.insertCart(cartDTO);
		
		return addCartResult;
	}
	
	@Override
	public void getCartList(HttpServletRequest request, Model model) {
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		List<CartDTO> cartList = cartMapper.selectCartList(userNo);
		model.addAttribute("cartList", cartList);
	}

}
