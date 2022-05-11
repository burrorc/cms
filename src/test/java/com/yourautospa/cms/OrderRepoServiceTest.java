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

import com.yourautospa.cms.dao.OrderRepository;
import com.yourautospa.cms.entity.Order;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepoServiceTest {

	@Autowired
	private OrderRepository repository;

	
	@Test
	public void testFindAll() {
		Order Order1 = new Order("order1");
		Order Order2 = new Order("order2");
		repository.save(Order1);
		repository.save(Order2);
		
		List<Order> OrderList = repository.findAll();
		Assertions.assertThat(OrderList).isNotNull();
		
	}

	@Test
	public void testFindById() {
		Order Order1 = new Order("order1");
		repository.save(Order1);
		Optional<Order> foundOrder = repository.findById(Order1.getId());
		
		Assertions.assertThat(foundOrder.get().getId()).isEqualTo(Order1.getId());
	}
	
	@Test
	public void testSave() {
		Order theOrder = new Order("order1");
		repository.save(theOrder);

		Assertions.assertThat(theOrder.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testDelete() {
		Order order1 = new Order();
		
		repository.save(order1);
		
		repository.deleteById(order1.getId());
		Optional<Order> foundOrder = repository.findById(order1.getId());
		
		
		
		Assertions.assertThat(foundOrder).isEmpty();
	}
	
	@Test
	public void testFindFirstByPlateOrderByCreatedOnDesc() {
		String thePlate = "XYZ";
		Order order1 = new Order();
		order1.setPlate(thePlate);
		repository.save(order1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Order order2 = new Order();
		order2.setPlate(thePlate);
		repository.save(order2);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Order order3 = new Order();
		order3.setPlate(thePlate);
		repository.save(order3);
		
		Order foundOrder = repository.findFirstByPlateOrderByCreatedOnDesc(thePlate);
		Assertions.assertThat(foundOrder.getId()).isEqualTo(order3.getId());
	}
}
