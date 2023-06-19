package com.gdu.book.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.book.domain.MeetingDTO;
import com.gdu.book.domain.MeetingMemberDTO;
import com.gdu.book.domain.OpenTalkBoardDetailDTO;

@Mapper
public interface MeetingMapper {

	public int getMeetingCount();
	public List<MeetingDTO> loadMeetingList(Map<String, Object> map);
	public int createMeeting(MeetingDTO meetingDTO);
	public MeetingDTO getMeetingByNo(int meetingNo);
	public int createApply(Map<String, Object> map);
	public int apply(Map<String, Object> map);
	public List<OpenTalkBoardDetailDTO> commentList(int meetingNo);
	public List<MeetingMemberDTO> participantList(int meetingNo);
	public int commentWrite(OpenTalkBoardDetailDTO openTalkBoardDetailDTO);
	public int getMemberCount(int meetingNo);
}
