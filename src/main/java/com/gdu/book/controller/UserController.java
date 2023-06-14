package com.gdu.book.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.book.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
	
	//필드
	private final UserService userService;
		
	@GetMapping("/agree.html")
	public String agreeForm() {
	  return "user/agree";
	}
	
	@GetMapping("/join.html")
	public String joinForm(@RequestParam(value="location", required=false) String location  // 파라미터 location이 전달되지 않으면 빈 문자열("")이 String location에 저장된다.
	                     , @RequestParam(value="event", required=false) String event        // 파라미터 event가 전달되지 않으면 빈 문자열("")이 String event에 저장된다.
	                     , Model model) {
	  model.addAttribute("location", location);
	  model.addAttribute("event", event);
	  return "user/join";
	}
	
	
	@ResponseBody
	@GetMapping(value="/verifyId.do", produces="application/json")
	public Map<String, Object> verifyId(@RequestParam("userId") String userId) {
	  return userService.verifyId(userId);
	}
	
	@ResponseBody
	@GetMapping(value="/verifyEmail.do", produces="application/json")
	public Map<String, Object> verifyEmail(@RequestParam("userEmail") String userEmail) {
	  return userService.verifyEmail(userEmail);
	}
	
	@ResponseBody
	@GetMapping(value="/sendAuthCode.do", produces="application/json")
	public Map<String, Object> sendAuthCode(@RequestParam("userEmail") String userEmail) {
	  return userService.sendAuthCode(userEmail);
	}
	
	@PostMapping("/join.do")
	public void join(HttpServletRequest request, HttpServletResponse response) {
	  userService.join(request, response);
	}
	
	
	@GetMapping("/login.html")
	public String loginForm(HttpServletRequest request, Model model) {
	  // 요청 헤더 referer : 로그인 화면으로 이동하기 직전의 주소를 저장하는 헤더 값
	  String url = request.getHeader("referer");
	  model.addAttribute("url", url == null ? request.getContextPath() : url);
	  return "user/login";
	}
	
	@PostMapping("/login.do")
	public void login(HttpServletRequest request, HttpServletResponse response) {
	  userService.login(request, response);
	}	

}
