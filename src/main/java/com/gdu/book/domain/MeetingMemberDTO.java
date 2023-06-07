package com.gdu.book.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingMemberDTO {
	private int meetingMemberNo;
	private UserDTO userDTO;
	private MeetingDTO meetingDTO;
	private int meetingMemberStatus;
}
