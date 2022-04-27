package com.yourautospa.cms.dao;

import java.util.List;

import com.yourautospa.cms.entity.Vehicle;

public interface VehicleDAO {
	public List<Vehicle> findAll();
	
	public Vehicle findById(String theId);
	
	public void save(Vehicle theVehicle);
	
	public void deleteById(String theId);
}
