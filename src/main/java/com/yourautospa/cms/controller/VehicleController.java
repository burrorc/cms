package com.yourautospa.cms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yourautospa.cms.entity.Vehicle;
import com.yourautospa.cms.service.VehicleJpaService;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
	
	private VehicleJpaService vehicleService;
	
	public VehicleController(VehicleJpaService theVehicleService) {
		vehicleService = theVehicleService;
	}
	
	@GetMapping("/list")
	public String listVehicles(Model theModel) {
		
		List<Vehicle> theVehicles = vehicleService.findAll();
		theModel.addAttribute("vehicles", theVehicles);
		
		return "vehicles/vehicle-list";
		
	}
	
	@GetMapping("/addUpdate")
	public String addUpdate(Model theModel){
		
		Vehicle theVehicle = new Vehicle();
		
		theModel.addAttribute("vehicle", theVehicle);
		
		return "vehicles/vehicle-form";
	}

	
}
