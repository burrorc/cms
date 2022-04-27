package com.yourautospa.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourautospa.cms.entity.Vehicle;

public interface VehicleRespository extends JpaRepository<Vehicle, String> {

}
