package com.gdu.book.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.book.service.ManagerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/manager")
@Controller
public class ManagerController {
	
	private final ManagerService managerService;
	
	@GetMapping("/manager.html")
	public String manager() {
		return "manager/manager";
	}
	
	@ResponseBody
    @GetMapping(value="/queryBook.do", produces="application/json")
    public String queryBook(HttpServletRequest request) {
      return managerService.queryBook(request);
    }
	
	@GetMapping("/addBook.do")
	public String addBook(HttpServletRequest request, Model model) {
		
		return null;
	}
	
}
