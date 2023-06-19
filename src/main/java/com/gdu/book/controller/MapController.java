package com.gdu.book.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {

	 @GetMapping("/map.html")
	 public String mapPage() {
		 return "map/map";
	 }

	
  
  
  

}
