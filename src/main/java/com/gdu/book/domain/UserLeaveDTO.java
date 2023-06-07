package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLeaveDTO {
  private String userLeaveId;
  private String userLeaveEmail;
  private Date joinedLeaveAt;
  private Date leavedAt;
}
