package com.gdu.book.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.book.domain.MeetingDTO;
import com.gdu.book.domain.UserDTO;
import com.gdu.book.service.MeetingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {

	private final MeetingService meetingService;
	
	@GetMapping("/list.html")
	public String list() {
		return "meeting/list";
	}
	
	@ResponseBody
	@GetMapping(value="meetingScroll.do", produces="application/json")
	public Map<String, Object> meetingScroll(HttpServletRequest request){
		return meetingService.loadMeetingList(request);
		
	}
	
	@GetMapping("/create.html")
	public String create() {
		return "meeting/create";
	}
	
	@PostMapping("/write.do")
	public String write(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
		int addResult = meetingService.createMeeting(multipartRequest);
		redirectAttributes.addAttribute("addResult", addResult);
		return "redirect:/meeting/list.html";
	}
	
	@GetMapping("/detail.html")
	public String detail(HttpServletRequest request, Model model) {
		meetingService.getMeetingByNo(request, model);
		return "meeting/detail";
	}
	
	@PostMapping(value="/apply.do", produces="application/json")
	@ResponseBody
	public Map<String, Object> apply(HttpServletRequest request) {
		return meetingService.apply(request);
	}
	
	@PostMapping(value="/comment.view", produces="application/json")
	@ResponseBody
	public Map<String, Object> comment(HttpServletRequest request){
		return meetingService.comment(request);
	}
	
	@PostMapping(value="/participant.view", produces="application/json")
	@ResponseBody
	public Map<String, Object> participant(HttpServletRequest request){
		return meetingService.participant(request);
	}
	
	@PostMapping(value="/comment.do", produces="application/json")
	@ResponseBody
	public Map<String, Object> commentWrite(HttpServletRequest request){
		return meetingService.commentWrite(request);
	}
	
}
