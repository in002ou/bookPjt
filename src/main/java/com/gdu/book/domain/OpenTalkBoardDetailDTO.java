package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenTalkBoardDetailDTO {
	private int openTalkBoardDetailNo;
	private int meetingNo;
	private UserDTO userDTO;
	private String boardDetailContent;
	private Date boardDetailContentAt;
}
