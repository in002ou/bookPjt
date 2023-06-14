package com.gdu.book.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.book.domain.MeetingDTO;

@Mapper
public interface MeetingMapper {

	public int createMeeting(MeetingDTO meetingDTO);
	
}
