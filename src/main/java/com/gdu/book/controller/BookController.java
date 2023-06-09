package com.gdu.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

  
  @GetMapping("/bookList.html")
  public String movieList() {
	  return "book/booklist";
  }
  
  @GetMapping("/bookDetail.html")
  public String movieDetail() {
	  return "book/bookDetail";
  }
  

}
