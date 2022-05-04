package com.yourautospa.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourautospa.cms.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
