package com.yourautospa.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yourautospa.cms.entity.Wash;
import com.yourautospa.cms.service.WashService;

@Controller
@RequestMapping("/washes")
public class WashController {
	
	@Autowired
	private WashService washService;
	
//	public VehicleController(VehicleService theVehicleService) {
//		washService = theVehicleService;
//	}
	
	@GetMapping("/list")
	public String listWashes(Model theModel) {
		
		List<Wash> theWashes = washService.findAll();
		theModel.addAttribute("washes", theWashes);
		
		return "washes/wash-list";
		
	}
	
	@GetMapping("/addForm")
	public String addForm(Model theModel){
		
		Wash theWash = new Wash();
		
		theModel.addAttribute("wash", theWash);
		
		return "washes/wash-form";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam("washPlate")int theId,
							Model theModel){
		
		Wash theWash = washService.findById(theId);
		
		theModel.addAttribute("wash", theWash);
		
		return "washes/wash-form";
	}
	
	@PostMapping("/save")
	public String saveVehicle(@ModelAttribute("wash") Wash theWash) {
	
	washService.save(theWash);
	
	return "redirect:/washes/list";
	
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("washPlate")int theId) {
		
	washService.deleteById(theId);
	
	return "redirect:/washes/list";
	}

	
}
