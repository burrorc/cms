package com.yourautospa.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourautospa.cms.dao.VehicleRepository;
import com.yourautospa.cms.entity.Vehicle;

@Service
public class VehicleServiceImpl implements VehicleService {

	private VehicleRepository vehicleRepository;

	@Autowired
	public VehicleServiceImpl(VehicleRepository theVehicleRepository) {
		vehicleRepository = theVehicleRepository;
	}

	@Override
	public List<Vehicle> findAll() {
		return vehicleRepository.findAllByOrderByPlateAsc();
	}
//	@Override
//	public List<Vehicle> findAll() {
//		return vehicleRepository.findAll();
//	}

	@Override
	public Vehicle findById(String theId) {
		// TODO Auto-generated method stub
		Optional<Vehicle> result = vehicleRepository.findById(theId);

		Vehicle theVehicle = null;

		if (result.isPresent()) {
			theVehicle = result.get();
		} else {
			throw new RuntimeException("Didn't find vehicle with plate - " + theId);
		}

		return theVehicle;
	}

	@Override
	public void save(Vehicle theVehicle) {
		if(theVehicle.getMake()=="") {
			theVehicle.setMake("UNKNOWN");
		}
//		if(theVehicle.getModel()=="") {
//			theVehicle.setModel("UNKNOWN");
//		}
		vehicleRepository.save(theVehicle);

	}

	@Override
	public void deleteById(String theId) {
		vehicleRepository.deleteById(theId);

	}

	@Override
	public Vehicle findOrAdd(String theId) {
		Optional<Vehicle> result = vehicleRepository.findById(theId);

		Vehicle theVehicle = null;

		if (result.isPresent()) {
			theVehicle = result.get();
		} else {
			theVehicle = new Vehicle(theId);
		}

		return theVehicle;
	}

}
