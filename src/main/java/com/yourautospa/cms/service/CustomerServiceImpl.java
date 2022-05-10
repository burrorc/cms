package com.yourautospa.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourautospa.cms.dao.CustomerRepository;
import com.yourautospa.cms.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

//	@Autowired
//	public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
//		customerRepository = theCustomerRepository;
//	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Customer> result = customerRepository.findById(theId);

		Customer theCustomer = null;

		if (result.isPresent()) {
			theCustomer = result.get();
		} else {
			throw new RuntimeException("Didn't find Customer with id - " + theId);
		}

		return theCustomer;
	}

	@Override
	public void save(Customer theCustomer) {
		
		customerRepository.save(theCustomer);

	}

	@Override
	public void deleteById(int theId) {
		customerRepository.deleteById(theId);

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
