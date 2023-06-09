package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userNo;					// 유저번호
    private String userId;				// 유저 아이디
    private String userPassword;		// 유저 비밀번호
    private String userName;			// 유저 이름
    private String userGender;			// 유저 성별
    private String userEmail;			// 유저 이메일
    private String userMobile;			// 유저 연락처
    private String userBirthyear;		// 유저 출생년도
    private String userBirthdate;		// 유저 출생월일
    private String postcode;			// 우편번호
    private String roadAddress;			// 도로명주소
    private String jibunAddress;		// 지번
    private String detailAddress;		// 상세주소
    private String extraAddress;		// 참고항목
    private int agreecode; 				// 회원가입동의
    private Date joinedAt; 				// 회원가입일
    private Date pwModifiedAt; 			// 비밀번호 수정일
    private String autologinId;			// 자동 로그인
    private Date autologinExpiredAt;	// 자동로그인 만료일
    private String userProfile;			// 유저의 프로필 사진
    private String userIntroduce;		// 유저의 자기소개
    private int userPoint;				// 유저의 포인트
    private int userGrade;				// 유저의 등급
    private int userSocial;				// 네이버 로그인, 카카오 로그인
}
