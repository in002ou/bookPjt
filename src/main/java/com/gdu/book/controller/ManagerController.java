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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/addBook.html")
	public String Book() {
		return "manager/addBook.html";
	}
	
	@GetMapping("/writeQna.html")
	public String writeQna() {
		return "manager/writeQna";
	}
	
	@GetMapping("/writeAnmt.html")
	public String writeAnmt() {
		return "manager/writeAnmt";
	}
	
	@GetMapping("/QnA.do")
	public String QnA(HttpServletRequest request, Model model) {
		managerService.QnaList(request, model);
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
	
	@PostMapping("/addQnA.do")
	public String addQnA(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("addResult", managerService.addQna(request));
		return "redirect:/manager/QnA.do";
	}
	
	@PostMapping("/answer.do")
	public String answer(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("updateResult", managerService.answerQna(request));
		return "redirect:/manager/QnA.do";
	}
	
	@PostMapping("/remove.do")
	public String remove(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("removeResult", managerService.deleteQna(request));
		return "redirect:/manager/QnA.do";
	}
	
	@PostMapping("/addAnmt.do")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		managerService.addAnmt(request, response);
	}
	
	@GetMapping("/anmt.do")
	public String anmt(HttpServletRequest request, Model model) {
		managerService.selectAnmt(request, model);
		return "manager/anmt";
	}
	
	@GetMapping("/anmtDetail.do")
	public String anmtDetail(@RequestParam(value="anmNo", required=false, defaultValue="0") int anmNo
            , HttpServletRequest request, Model model) {
		model.addAttribute("anmDTO", managerService.anmtDetail(anmNo, request, model));
		return "manager/anmtDetail";
	}
	
	@ResponseBody
	@PostMapping(value="/imageUpload.do", produces="application/json")
	public Map<String, Object> imageUpload(MultipartHttpServletRequest multipartRequest) {
		return managerService.imageUpload(multipartRequest);
	}
	
	@PostMapping("/removeAnmt.do")
	public void removeAnmt(HttpServletRequest request, HttpServletResponse response) {
		managerService.removeAnmt(request, response);
		
	}
	
	@GetMapping("/edit.do")
	public String edit(@RequestParam(value="anmNo", required=false, defaultValue="0") int anmNo
            			, HttpServletRequest request, Model model) {
		model.addAttribute("anmDTO", managerService.anmtDetail(anmNo, request, model));
		return "manager/edit";
	}
	
	@PostMapping("/modify.do")
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		
		managerService.modifyAnmt(request, response);
	}
	
	@PostMapping("/declaration.html")
	public String declaration() {
		return "manager/declaration";
	}
	
	@PostMapping("/addDec.do")
	public String addDec(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
	
	@GetMapping("/answerAdd.do")
	public Map<String, Object> addQna(HttpServletRequest request){
		managerService.answerAdd(request);
		return managerService.answerAdd(request);
	}
	
}
