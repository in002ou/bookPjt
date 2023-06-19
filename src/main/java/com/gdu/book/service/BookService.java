package com.gdu.book.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.gdu.book.domain.BookSearchDTO;


public interface BookService {
	public Map<String, Object> searchBook(BookSearchDTO bookSearchDTO);
	public void getBookList(HttpServletRequest request, Model model);
	public void getBookDetail(HttpServletRequest request, Model model);
	public int addBookReview(HttpServletRequest request);
	public int deleteBookReview(HttpServletRequest request);
	public int bookLike(HttpServletRequest request, Model model);
	public int bookDisLike(HttpServletRequest request, Model model);
}
