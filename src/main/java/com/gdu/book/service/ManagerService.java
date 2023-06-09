package com.gdu.book.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface ManagerService {

	public String queryBook(HttpServletRequest request);
	public int addBook(HttpServletRequest request, Model model);
}
