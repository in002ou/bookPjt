package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeclarationDTO {
 private int declarationNo;
 private int userNo;
 private int bookReviewNo;
 private String declarationContent;
 private Date declarationAt;
 private int descState;
 private int receivedUserNo;
}
