package com.gdu.book.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface ManagerService {

	public String queryBook(HttpServletRequest request);
	public Map<String, Object> addBook(HttpServletRequest request);
}
