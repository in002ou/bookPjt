package com.gdu.book.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.book.domain.MeetingDTO;

public interface MeetingService {
	public int createMeeting(MultipartHttpServletRequest multipartRequest);
	public Map<String, Object> loadMeetingList(HttpServletRequest request);
	public void getMeetingByNo(HttpServletRequest request, Model model);
}
