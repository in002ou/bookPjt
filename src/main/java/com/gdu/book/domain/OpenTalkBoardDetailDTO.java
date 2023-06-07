package com.gdu.book.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenTalkBoardDetailDTO {
	private int openTalkBoardDetailNo;
	private OpenTalkBoardDTO openTalkBoardDTO;
	private UserDTO userDTO;
	private String boardDetailContent;
}
