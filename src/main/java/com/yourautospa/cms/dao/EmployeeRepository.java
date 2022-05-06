package com.yourautospa.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourautospa.cms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
