package com.gdu.book.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;


public interface BookService {
	public void getBookList(HttpServletRequest request, Model model);
	public void getBookDetail(HttpServletRequest request, Model model);
	public int addBookReview(HttpServletRequest request);
	public int deleteBookReview(HttpServletRequest request);
}
