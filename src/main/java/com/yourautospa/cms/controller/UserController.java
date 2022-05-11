package com.yourautospa.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yourautospa.cms.entity.User;
import com.yourautospa.cms.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//THIS IS BCRYPT
	@Autowired    
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/list")
	public String listUsers(Model theModel) {
		
		List<User> theUsers = userService.findAll();
		theModel.addAttribute("users", theUsers);
		
		return "users/user-list";
		
	}
	
	@GetMapping("/addForm")
	public String addForm(Model theModel){
		
		User theUser = new User();
		
		theModel.addAttribute("user", theUser);
		
		return "users/user-form";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam("userId")int theId,
							Model theModel){
		
		User theUser = userService.findById(theId);
		
		theModel.addAttribute("user", theUser);
		
		return "users/user-form";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User theUser) {
	
	String thePassword = theUser.getPassword();
	if(thePassword.length() < 20) {
		System.out.println("less than 20");
		String encodedPassword = bCryptPasswordEncoder.encode(theUser.getPassword());
		theUser.setPassword(encodedPassword);
	}
	
	userService.save(theUser);
	
	return "redirect:/users/list";
	
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("userId")int theId) {
		
	userService.deleteById(theId);
	
	return "redirect:/users/list";
	}

	
}
