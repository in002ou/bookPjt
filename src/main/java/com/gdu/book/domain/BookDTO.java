package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
	private String bookNo;
	private String bookTitle;
	private int bookPrice;
	private Date bookAt;
	private String bookAuthor;
	private String bookImage;
	private String bookDescription;
	private int bookState;
}
