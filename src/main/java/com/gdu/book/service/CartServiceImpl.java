package com.gdu.book.service;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public int addCart(HttpServletRequest request) {
		String bookNo = request.getParameter("bookNo");
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
		int totalPrice = 0;
		for(int i = 0; i < cartList.size(); i++) {
			for(int x = 0; x < cartList.get(i).getCount(); x++) {
				totalPrice += cartList.get(i).getBookDTO().getBookPrice();
			}
		}
		
		
		
		
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("cartList", cartList);
	}
	
	@Override
	public void removeCart(HttpServletRequest request, HttpServletResponse response) {
		String[] cartNoList = request.getParameterValues("cartNo");
		
		int removeResult = cartMapper.deleteCart(Arrays.asList(cartNoList));
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(removeResult == cartNoList.length) {
				out.println("alert('선택된 도서가 모두 삭제되었습니다')");
				out.println("location.href='" + request.getContextPath() + "/cart/cartList.do?userNo=" + request.getParameter("userNo") + "'");
			}else {
				out.println("alert('선택된 도서가 삭제되지 않았습니다')");
				out.println("history.back()");
			}
			out.println("</script>");
			out.flush();
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getcartDetail(HttpServletRequest request, Model model) {
		int userNo = 1;
		
		List<CartDTO> cartList = cartMapper.selectCartList(userNo);
		int totalPrice = 0;
		for(int i = 0; i < cartList.size(); i++) {
			for(int x = 0; x < cartList.get(i).getCount(); x++) {
				totalPrice += cartList.get(i).getBookDTO().getBookPrice();
			}
		}
		int orderNo = (int)Math.floor(Math.random() * 10000000) + 1;
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("cartList", cartList);
		
	}

}
