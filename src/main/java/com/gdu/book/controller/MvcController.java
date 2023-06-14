package com.gdu.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MvcController {

  
  @GetMapping({"/", "/index.do"})
  public String welcom(Model model) {
    return "index";
  }
  

}
