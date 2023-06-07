package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSleepDTO {
  private int userSleepNo;
  private String userSleepId;
  private String userSleepPassword;
  private String userSleepName;
  private String userSleepGender;
  private String userSleepEmail;
  private String userSleepMobile;
  private String userSleepBirthyear;
  private String userSleepBirthdate;
  private String userSleepPostcode;
  private String userSleepRoadAddress;
  private String userSleepJibunAddress;
  private String userSleepDetailAddress;
  private String userSleepExtraAddress;
  private int userSleepAgreecode;
  private Date userSleepJoinedAt;
  private Date userSleepPwModifiedAt;
  private String userSleepProfile;
  private String userSleepIntroduce;
  private int userSleepPoint;
  private int userSleepGrade;
  private int userSleepSocial;
  private Date userSleeptAt;
}
