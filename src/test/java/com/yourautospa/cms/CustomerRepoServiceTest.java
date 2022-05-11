package com.yourautospa.cms;


import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yourautospa.cms.dao.CustomerRepository;
import com.yourautospa.cms.entity.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepoServiceTest {

	@Autowired
	private CustomerRepository repository;

	@Test
	public void testFindAll() {
		Customer Customer1 = new Customer("testy", "mctesty");
		Customer Customer2 = new Customer("joe", "shmoe");
		repository.save(Customer1);
		repository.save(Customer2);
		
		List<Customer> CustomerList = repository.findAll();
		Assertions.assertThat(CustomerList).isNotNull();
		
	}

	@Test
	public void testFindById() {
		Customer Customer1 = new Customer("testy", "mctesty");
		repository.save(Customer1);
		Optional<Customer> foundCustomer = repository.findById(Customer1.getId());
		
		Assertions.assertThat(foundCustomer.get().getId()).isEqualTo(Customer1.getId());
	}
	
	@Test
	public void testSave() {
		Customer theCustomer = new Customer("testy", "mctesty");
		repository.save(theCustomer);

		Assertions.assertThat(theCustomer.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testDeleteById() {
		Customer Customer1 = new Customer("testy", "mctesty");
		repository.save(Customer1);
		repository.deleteById(Customer1.getId());
		Optional<Customer> foundCustomer = repository.findById(Customer1.getId());
		
		Assertions.assertThat(foundCustomer.isEmpty());
	}
	
}
