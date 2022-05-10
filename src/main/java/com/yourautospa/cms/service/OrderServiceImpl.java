package com.yourautospa.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourautospa.cms.dao.OrderRepository;
import com.yourautospa.cms.entity.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Order> result = orderRepository.findById(theId);

		Order theOrder = null;

		if (result.isPresent()) {
			theOrder = result.get();
		} else {
			throw new RuntimeException("Didn't find Order with id - " + theId);
		}

		return theOrder;
	}

	@Override
	public void save(Order theOrder) {
		
		orderRepository.save(theOrder);

	}

	@Override
	public void deleteById(int theId) {
		orderRepository.deleteById(theId);

	}

	@Override
	public Order findFirstByPlateOrderByCreatedOnDesc(String thePlate) {
		// TODO Auto-generated method stub
		return orderRepository.findFirstByPlateOrderByCreatedOnDesc(thePlate);
	}

//	@Override
//	public Wash findOrAdd(String theId) {
//		Optional<Wash> result = washRepository.findById(theId);
//
//		Wash theWash = null;
//
//		if (result.isPresent()) {
//			theWash = result.get();
//		} else {
//			theWash = new Wash(theId);
//		}
//
//		return theWash;
//	}

	
}
