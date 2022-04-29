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

import com.yourautospa.cms.entity.Vehicle;
import com.yourautospa.cms.service.VehicleService;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
//	public VehicleController(VehicleService theVehicleService) {
//		vehicleService = theVehicleService;
//	}
	
	@GetMapping("/list")
	public String listVehicles(Model theModel) {
		
		List<Vehicle> theVehicles = vehicleService.findAll();
		theModel.addAttribute("vehicles", theVehicles);
		
		return "vehicles/vehicle-list";
		
	}
	
	@GetMapping("/addForm")
	public String addForm(Model theModel){
		
		Vehicle theVehicle = new Vehicle();
		
		theModel.addAttribute("vehicle", theVehicle);
		
		return "vehicles/vehicle-form";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam("vehiclePlate")String theId,
							Model theModel){
		
		Vehicle theVehicle = vehicleService.findById(theId);
		
		theModel.addAttribute("vehicle", theVehicle);
		
		return "vehicles/vehicle-form";
	}
	
	@PostMapping("/save")
	public String saveVehicle(@ModelAttribute("vehicle") Vehicle theVehicle) {
	
	vehicleService.save(theVehicle);
	
	return "redirect:/vehicles/list";
	
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("vehiclePlate")String theId) {
		
	vehicleService.deleteById(theId);
	
	return "redirect:/vehicles/list";
	}

	
}
