package com.gdu.book.service;

import org.springframework.web.multipart.MultipartFile;

import com.gdu.book.domain.MeetingDTO;

public interface MeetingService {
	public int createMeeting(MeetingDTO meetingDTO, MultipartFile imageFile);
}
