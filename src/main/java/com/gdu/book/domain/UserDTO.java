package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userNo;
    private String userId;
    private String userPassword;
    private String userName;
    private String userGender;
    private String userEmail;
    private String userMobile;
    private String userBirthyear;
    private String userBirthdate;
    private String postcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String extraAddress;
    private int agreecode;
    private Date joinedAt;
    private Date pwModifiedAt;
    private String autologinId;
    private Date autologinExpiredAt;
    private String userProfile;
    private String userIntroduce;
    private int userPoint;
    private int userGrade;
    private int userSocial;
}
