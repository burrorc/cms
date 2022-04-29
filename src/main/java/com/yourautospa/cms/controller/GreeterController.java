package com.yourautospa.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yourautospa.cms.entity.Vehicle;
import com.yourautospa.cms.entity.Wash;
import com.yourautospa.cms.service.VehicleService;
import com.yourautospa.cms.service.WashService;

@Controller
@RequestMapping("/greeter")
public class GreeterController {

	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private WashService washService;


	@GetMapping("/order")
	public String greeter(Model theModel) {
		Vehicle theVehicle = new Vehicle();
		Wash theWash = new Wash();
		List<Wash> theWashes = washService.findAll();
		
			theVehicle.setPlate("GA");
		
		
		
		theModel.addAttribute("vehicle", theVehicle);
		theModel.addAttribute("washes", theWashes);
		theModel.addAttribute("wash", theWash);

		return "greeter/orderForm";
	}

//	@GetMapping("/lookupVehicle")
//	public String lookupVehicle(@RequestParam("vehiclePlate")String theId,
//							Model theModel){
//		
//		Vehicle theVehicle = vehicleService.findById(theId);
//		
//		theModel.addAttribute("vehicle", theVehicle);
//		
//		return "vehicles/vehicle-form";
//	}

	@PostMapping("/addCar")
	public String saveVehicle(@ModelAttribute("vehicle") Vehicle theVehicle, Model theModel) {
		String thePlate = theVehicle.getPlate();
		Vehicle tempVehicle = vehicleService.findOrAdd(thePlate);
		Wash tempWash = washService.findById(tempVehicle.getSubscription());
		List<Wash> theWashes = washService.findAll();
		
	vehicleService.save(tempVehicle);
	theModel.addAttribute(tempVehicle);
	theModel.addAttribute(tempWash);
	theModel.addAttribute("washes", theWashes);
	
	return "greeter/orderForm";
	
	}
//	@PostMapping("/addCar")
//	public String addCar(@ModelAttribute("vehicle") Vehicle theVehicle, Model theModel) {
//
//		//String thePlate = theVehicle.getPlate();
//		
//		vehicleService.save(theVehicle);
//		
//		//Vehicle tempVehicle = vehicleService.findOrAdd(thePlate);
//
//		theModel.addAttribute("vehicle", theVehicle);
//
//		return "greeter";
//
//	}
	@GetMapping("/clear")
	public String clear(Model theModel) {
		 return "redirect:/greeter/order";
//		Vehicle theVehicle = new Vehicle();
//		theVehicle.setPlate("GA");
//		
//		theModel.addAttribute("vehicle", theVehicle);
//
//		return "greeter/orderForm";
	}
}
