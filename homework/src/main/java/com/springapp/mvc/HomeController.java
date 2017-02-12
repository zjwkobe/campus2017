package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping(method = RequestMethod.POST)
	public String printWelcome(ModelMap model) {
		//&nbsp
		model.addAttribute("title", "please input");
		model.addAttribute("message", "please input");
		model.addAttribute("head", "home page");
		model.addAttribute("first_name","&nbsp");
		model.addAttribute("first_times","&nbsp");
		model.addAttribute("second_name", "&nbsp");
		model.addAttribute("second_times", "&nbsp");
		model.addAttribute("third_name", "&nbsp");
		model.addAttribute("third_times", "&nbsp");
		return "typein";
	}
	@RequestMapping(method = RequestMethod.GET)
	public String Welcome(ModelMap model) {
		//&nbsp
		model.addAttribute("title", "please input");
		model.addAttribute("message", "please input");
		model.addAttribute("head", "home page");
		model.addAttribute("first_name","&nbsp");
		model.addAttribute("first_times","&nbsp");
		model.addAttribute("second_name", "&nbsp");
		model.addAttribute("second_times", "&nbsp");
		model.addAttribute("third_name", "&nbsp");
		model.addAttribute("third_times", "&nbsp");
		return "typein";
	}

}