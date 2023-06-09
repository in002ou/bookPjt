package com.gdu.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.book.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
	
	//필드
	private final UserService userService;

}
