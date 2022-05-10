package com.yourautospa.cms.service;

import java.util.List;

import com.yourautospa.cms.entity.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public Customer findById(int theId);

	public void save(Customer theCustomer);

	public void deleteById(int theId);
	
	

	//public Subscription findOrAdd(String theId);
}
