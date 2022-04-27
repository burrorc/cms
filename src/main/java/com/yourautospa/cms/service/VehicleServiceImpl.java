package com.yourautospa.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yourautospa.cms.dao.VehicleDAO;
import com.yourautospa.cms.entity.Vehicle;

@Service
public class VehicleServiceImpl implements VehicleService {

	private VehicleDAO vehicleDAO;
	
	@Autowired
	public VehicleServiceImpl(VehicleDAO theVehicleDAO) {
		vehicleDAO = theVehicleDAO;
	}

	@Override
	@Transactional
	public List<Vehicle> findAll() {
		return vehicleDAO.findAll();
	}

	@Override
	@Transactional
	public Vehicle findById(String theId) {
		return vehicleDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Vehicle theVehicle) {
		vehicleDAO.save(theVehicle);
	}

	@Override
	@Transactional
	public void deleteById(String theId) {
		vehicleDAO.deleteById(theId);

	}

}
