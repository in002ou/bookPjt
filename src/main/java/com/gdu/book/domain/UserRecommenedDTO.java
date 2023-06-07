package com.gdu.book.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRecommenedDTO {
 private UserDTO userDTO;
 private BookDTO bookDTO;
 private int recommened;
}
