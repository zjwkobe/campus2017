package com.qunar.CharacterCounter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping("/hello")
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}
	@RequestMapping("")
	public String indexPage(){
		return "index";
	}
	@RequestMapping("/toUpload")
	public String uploadPage(){
		return "upload";
	}



}