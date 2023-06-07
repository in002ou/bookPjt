package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReviewDTO {
 private BookDTO bookDTO;
 private UserDTO userDTO;
 private String bookReview;
 private double bookRating;
 private Date bookReviewCreatedAt;
}
