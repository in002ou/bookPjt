package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReviewDTO {
	private int bookReviewNo;
	private String bookNo;
	private UserDTO userDTO;
	private String bookReview;
	private double bookRating;
	private Date bookReviewCreatedAt;
}
