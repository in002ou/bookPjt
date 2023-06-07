package com.gdu.book.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {
 private int anmNo;
 private String title;
 private String anmContent;
 private Date registration;
 private String image;
 private int anmCount;
}
