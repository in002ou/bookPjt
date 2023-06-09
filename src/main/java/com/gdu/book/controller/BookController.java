package com.gdu.book.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.book.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

  private final BookService bookService; 
	
  @GetMapping("/bookList.html")
  public String movieList() {
	  return "book/booklist";
  }
  
  @GetMapping("/bookDetail.html")
  public String movieDetail() {
	  return "book/bookDetail";
  }
  @PostMapping("/bookReviewadd.do")
  public String bookReviewadd(Model model, HttpServletRequest request) {
	  model.addAttribute("addResult", bookService.addBookReview(request));
	  return "book/bookDetail";
  }
  

}
