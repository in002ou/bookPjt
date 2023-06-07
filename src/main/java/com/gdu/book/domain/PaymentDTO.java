package com.gdu.book.domain;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
 private int paymentNo;
 private UserDTO userDTO;
 private CartDTO cartDTO;
 private Date paymentDateAt;
 private String paymentOption;
 private String paymentCardCompany;
 private String cardNo;
 private int totalPaymentCost;
}
