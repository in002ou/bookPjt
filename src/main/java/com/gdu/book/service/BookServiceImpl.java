package com.gdu.book.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

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
	public int addBookReview(HttpServletRequest request) {
		String review = request.getParameter("review");
		int rating = Integer.parseInt(request.getParameter("rating"));
		
		BookReviewDTO bookReviewDTO = new BookReviewDTO();
		UserDTO userDTO = new UserDTO();
		BookDTO bookDTO = new BookDTO();
		bookDTO.setBookNo(1);
		userDTO.setUserNo(1);
		bookReviewDTO.setBookReview(review);
		bookReviewDTO.setBookRating(rating);
		bookReviewDTO.setUserDTO(userDTO);
		bookReviewDTO.setBookDTO(bookDTO);
		
		int addresult = bookMapper.insertBookReview(bookReviewDTO);
		
		return addresult;
	}
}
