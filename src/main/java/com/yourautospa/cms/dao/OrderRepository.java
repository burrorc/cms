package com.yourautospa.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourautospa.cms.entity.Order;
import com.yourautospa.cms.entity.Vehicle;


public interface OrderRepository extends JpaRepository<Order, Integer> {

	public Order findFirstByPlateOrderByCreatedOnDesc(String thePlate);

}
