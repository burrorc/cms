package com.yourautospa.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourautospa.cms.entity.Vehicle;
import com.yourautospa.cms.service.VehicleJpaService;

@RestController
@RequestMapping("/jpa")
public class VehicleJpaController {

	private VehicleJpaService vehicleService;

	// dirty injection
	@Autowired
	public VehicleJpaController(VehicleJpaService theVehicleService) {
		vehicleService = theVehicleService;
	}

	@GetMapping("/vehicles")
	public List<Vehicle> findAll() {
		return vehicleService.findAll();
	}

	@GetMapping("/vehicles/{vehicleId}")
	public Vehicle getVehicle(@PathVariable String vehicleId) {

		Vehicle theVehicle = vehicleService.findById(vehicleId);

		if (theVehicle == null) {
			throw new RuntimeException("Vehicle with plate " + vehicleId + " is not found");
		}

		return theVehicle;
	}

	@PostMapping("/vehicles")
	public Vehicle addVehicle(@RequestBody Vehicle theVehicle) {

		// have to manually set the plate id
		String thePlate = theVehicle.getPlate();
		theVehicle.setPlate(thePlate);
		vehicleService.save(theVehicle);

		return theVehicle;
	}

	@PutMapping("/vehicles")
	public Vehicle updateVehicle(@RequestBody Vehicle theVehicle) {

		vehicleService.save(theVehicle);

		return theVehicle;
	}
	
	@DeleteMapping("/vehicles/{vehicleId}")
	public String deleteVehicle(@PathVariable String vehicleId) {
		
		Vehicle tempVehicle = vehicleService.findById(vehicleId);
		
		//for bad id
		if (tempVehicle == null) {
			throw new RuntimeException("Vehicle with plate " + vehicleId + " is not found");
		}
		
		vehicleService.deleteById(vehicleId);
		
		return "Deleted "+ vehicleId + " from the system";
	}

}
