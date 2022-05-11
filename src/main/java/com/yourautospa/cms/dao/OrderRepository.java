package com.yourautospa.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourautospa.cms.entity.Order;
import com.yourautospa.cms.entity.Vehicle;


public interface OrderRepository extends JpaRepository<Order, Integer> {

	public Order findFirstByVehicleOrderByCreatedOnDesc(Vehicle theVehicle);
	
	public List<Order> findAllByCompletedOnIsNullOrderByCreatedOn();

}
