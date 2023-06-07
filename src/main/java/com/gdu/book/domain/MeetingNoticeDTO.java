package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingNoticeDTO {
	private int noticeNo;
	private int meetingNo;
	private String noticeName;
	private String noticeContent;
	private Date noticeAt;
}
