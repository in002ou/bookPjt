package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDTO {
	private int meetingNo;
	private UserDTO userDTO;
	private String meetingName;
	private int meetingCount;
	private String image;
	private int capacity;
	private Date meetingStartAt;
	private Date meetingEndAt;
	private String category;
	private String meetingContent;
}
