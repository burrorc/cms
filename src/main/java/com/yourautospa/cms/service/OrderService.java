package com.yourautospa.cms.service;

import java.util.List;

import com.yourautospa.cms.entity.Order;
import com.yourautospa.cms.entity.Vehicle;

public interface OrderService {

	public List<Order> findAll();

	public Order findById(int theId);

	public void save(Order theOrder);

	public void deleteById(int theId);

	public Order findFirstByPlateOrderByCreatedOnDesc(String thePlate);

	//public Subscription findOrAdd(String theId);
}
