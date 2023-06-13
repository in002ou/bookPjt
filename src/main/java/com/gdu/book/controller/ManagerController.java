package com.gdu.book.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/QnA.html")
	public String QnA() {
		return "manager/QnA";
	}
	
	@ResponseBody
    @GetMapping(value="/queryBook.do", produces="application/json")
    public String queryBook(HttpServletRequest request) {
      return managerService.queryBook(request);
    }
	
	@ResponseBody
	@GetMapping(value="/addBook.do", produces="application/json")
	public Map<String, Object> addBook(HttpServletRequest request) {
		
		return managerService.addBook(request);
	}
	
	@PostMapping("addQnA.html")
	public String addQnA(HttpServletRequest request, Model model) {
		
		return null;
	}
	
}
