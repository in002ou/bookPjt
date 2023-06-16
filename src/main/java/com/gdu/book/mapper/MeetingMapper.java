package com.gdu.book.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.book.domain.MeetingDTO;

@Mapper
public interface MeetingMapper {

	public int getMeetingCount();
	public List<MeetingDTO> loadMeetingList(Map<String, Object> map);
	public int createMeeting(MeetingDTO meetingDTO);
	public MeetingDTO getMeetingByNo(Map<String, Object> map);
}
