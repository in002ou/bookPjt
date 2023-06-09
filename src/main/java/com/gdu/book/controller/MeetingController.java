package com.gdu.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {

	@GetMapping("/list.html")
	public String list() {
		return "meeting/list";
	}
	
	@GetMapping("/create.html")
	public String create() {
		return "meeting/create";
	}
	
	@GetMapping("/screen.html")
	public String screen() {
		return "meeting/screen";
	}
	
}
