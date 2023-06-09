package com.gdu.book.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.book.domain.AnnouncementDTO;




public interface ManagerService {
	
	// 책 등록
	public String queryBook(HttpServletRequest request);
	public Map<String, Object> addBook(HttpServletRequest request);
	
	// Qna
	public int addQna(HttpServletRequest request);
	public void QnaList(HttpServletRequest request, Model model);
	public int answerQna(HttpServletRequest request);
	public int deleteQna(HttpServletRequest request);
	public Map<String, Object> answerAdd(HttpServletRequest request);
	
	// 공지사항
	public void addAnmt(HttpServletRequest request, HttpServletResponse response);
	public void selectAnmt(HttpServletRequest request, Model model);
	public AnnouncementDTO anmtDetail(int anmNo, HttpServletRequest request, Model model);
	public Map<String, Object> imageUpload(MultipartHttpServletRequest multipartRequest);
	public void removeAnmt(HttpServletRequest request, HttpServletResponse response);
	public void modifyAnmt(HttpServletRequest request, HttpServletResponse response);
	
	// 신고
	public void addDec(HttpServletRequest request, HttpServletResponse response);
}
