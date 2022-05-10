package com.yourautospa.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourautospa.cms.dao.VehicleRepository;
import com.yourautospa.cms.entity.Vehicle;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public List<Vehicle> findAll() {
		return vehicleRepository.findAllByOrderByPlateAsc();
	}


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
