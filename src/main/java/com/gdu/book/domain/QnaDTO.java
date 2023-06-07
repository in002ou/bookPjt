package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnaDTO {
 private int qnaNo;
 private UserDTO userDTO;
 private String title;
 private String answer;
 private int qnaState;
 private Date answerAt;
 private int depth;
 private int groupNo;
}
