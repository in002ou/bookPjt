package com.gdu.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.book.domain.MeetingDTO;
import com.gdu.book.domain.UserDTO;
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
	public String write(@RequestParam("userNo") int userNo, MeetingDTO meetingDTO, MultipartFile imageFile, RedirectAttributes redirectAttributes) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserNo(userNo);
		meetingDTO.setUserDTO(userDTO);
		int addResult = meetingService.createMeeting(meetingDTO, imageFile);
		redirectAttributes.addAttribute("addResult", addResult);
		return "redirect:/meeting/list.html";
	}
	
	@GetMapping("/detail.html")
	public String screen() {
		return "meeting/detail";
	}
	
}
