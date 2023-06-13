package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
 private int cartNo;
 private UserDTO userDTO;
 private BookDTO bookDTO;
 private int count;
 private Date cartCreatedAt;
}
