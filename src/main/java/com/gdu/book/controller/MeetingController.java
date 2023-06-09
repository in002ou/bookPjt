package com.gdu.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.book.service.MeetingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {

	private MeetingService meetingService;
	
	@GetMapping("/list.html")
	public String list() {
		return "meeting/list";
	}
	
	@GetMapping("/create.html")
	public String create() {
		return "meeting/create";
	}
	
	@PostMapping("/write.do")
	public String write() {
		return "redirect:/meeting/list.html";
	}
	
	@GetMapping("/detail.html")
	public String screen() {
		return "meeting/detail";
	}
	
}
