package com.gdu.book.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.book.domain.BookDTO;
import com.gdu.book.domain.BookReviewDTO;
import com.gdu.book.domain.UserDTO;
import com.gdu.book.mapper.BookMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	public final BookMapper bookMapper;
	
	@Override
	public void getBookDetail(HttpServletRequest request, Model model) {
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		BookDTO bookDetail = bookMapper.selectBookDetail(bookNo);
		List<BookReviewDTO> bookReview = bookMapper.selectBookReview(bookNo);
		model.addAttribute("bookDetail", bookDetail);
		model.addAttribute("bookReview", bookReview);
	}
	
	@Override
	public int addBookReview(HttpServletRequest request) {
		String review = request.getParameter("review");
		int rating = Integer.parseInt(request.getParameter("rating"));
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		
		BookReviewDTO bookReviewDTO = new BookReviewDTO();
		UserDTO userDTO = new UserDTO();
		BookDTO bookDTO = new BookDTO();
		bookDTO.setBookNo(bookNo);
		userDTO.setUserNo(1); // html에서 세션 스코프 사용해서 넘겨줘야함 (동형이 등록 코드 완성 후)
		bookReviewDTO.setBookReview(review);
		bookReviewDTO.setBookRating(rating);
		bookReviewDTO.setUserDTO(userDTO);
		bookReviewDTO.setBookDTO(bookDTO);
		
		int addresult = bookMapper.insertBookReview(bookReviewDTO);
		
		return addresult;
	}
}
