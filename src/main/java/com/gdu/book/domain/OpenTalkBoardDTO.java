package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenTalkBoardDTO {
	private int openTalkBoardNo;
	private MeetingDTO meetingDTO;
	private String boardContent;
	private Date boardStartAt;
	private String boardName;
}
