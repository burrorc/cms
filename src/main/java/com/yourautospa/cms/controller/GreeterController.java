package com.yourautospa.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreeterController {

	@GetMapping("/greeter")
	public String greeter(Model theModel) {
		
		theModel.addAttribute("greeterName", "John");
		
		return "greeter";
	}
}
