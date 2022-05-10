package com.yourautospa.cms;


import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yourautospa.cms.dao.EmployeeRepository;
import com.yourautospa.cms.entity.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeServiceImplTest {

	@Autowired
	private EmployeeRepository repository;

	@Test
	public void testSave() {
		Employee theEmployee = new Employee("testy", "mctesty");
		repository.save(theEmployee);

		Assertions.assertThat(theEmployee.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testFindAll() {
		Employee employee1 = new Employee("testy", "mctesty");
		Employee employee2 = new Employee("joe", "shmoe");
		repository.save(employee1);
		repository.save(employee2);
		
		List<Employee> employeeList = repository.findAll();
		Assertions.assertThat(employeeList).isNotNull();
		
	}

	@Test
	public void testFindById() {
		Employee employee1 = new Employee("testy", "mctesty");
		repository.save(employee1);
		Optional<Employee> foundEmployee = repository.findById(employee1.getId());
		
		Assertions.assertThat(foundEmployee.get().getId()).isEqualTo(employee1.getId());
	}
	
	@Test
	public void testUpdate() {
		Employee employee1 = new Employee("testy", "mctesty");
		
		repository.save(employee1);
		String firstTime = employee1.getFirstName();
		employee1.setFirstName("bob");
		repository.save(employee1);
		String secondTime = employee1.getFirstName();
		
		assertTrue(!firstTime.equals(secondTime));
	}
	
	public void testDelete() {
		Employee employee1 = new Employee("testy", "mctesty");
		
		repository.save(employee1);
		int theId = employee1.getId();
		repository.deleteById(theId);
		Employee foundEmployee = repository.getById(theId);
		
		Assertions.assertThat(foundEmployee).isNull();
	}
	
}
