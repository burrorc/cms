package com.yourautospa.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourautospa.cms.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

	public List<Vehicle> findAllByOrderByPlateAsc();
}
