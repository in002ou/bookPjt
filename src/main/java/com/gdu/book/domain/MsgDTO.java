package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgDTO {
	 private int msgNo;
	 private UserDTO userDTO;
	 private int msgState;
	 private String msgTitle;
	 private String msgContent;
	 private Date sendAt;
	 private int receiveState;
	 private int sendState;
	 private int receiver;
	 private Date receiveAt;
}
