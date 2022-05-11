package com.yourautospa.cms;


import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yourautospa.cms.dao.ProductRepository;
import com.yourautospa.cms.entity.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepoServiceTest {

	@Autowired
	private ProductRepository repository;

	@Test
	public void testFindAll() {
		Product Product1 = new Product(99);
		Product Product2 = new Product(100);
		repository.save(Product1);
		repository.save(Product2);
		
		List<Product> ProductList = repository.findAll();
		Assertions.assertThat(ProductList).isNotNull();
		
	}

	@Test
	public void testFindById() {
		Product Product1 = new Product(101);
		repository.save(Product1);
		Optional<Product> foundProduct = repository.findById(101);
		
		Assertions.assertThat(foundProduct.get().getId()).isEqualTo(Product1.getId());
	}
	
	@Test
	public void testSave() {
		Product theProduct = new Product(102);
		repository.save(theProduct);

		assertTrue(theProduct.getId() == 102);
	}
	
	@Test
	public void testDeleteById() {
		Product Product1 = new Product(999);
		repository.save(Product1);
		repository.deleteById(Product1.getId());
		Optional<Product> foundProduct = repository.findById(Product1.getId());
		
		Assertions.assertThat(foundProduct.isEmpty());
	}
	
	@Test
	public void testfindBySubscriptionTrue() {
		Product Product1 = new Product(99);
		Product1.setSubscription(true);
		repository.save(Product1);
		
		
		List<Product> ProductList = repository.findBySubscriptionTrue();
		Assertions.assertThat(ProductList).isNotNull();
	}
	
	@Test
	public void testfindByWashTrue() {
		Product Product1 = new Product(99);
		Product1.setWash(true);
		repository.save(Product1);
		
		
		List<Product> ProductList = repository.findByWashTrue();
		Assertions.assertThat(ProductList).isNotNull();
	}
	
	@Test
	public void testfindByExtraTrue() {
		Product Product1 = new Product(99);
		Product1.setExtra(true);
		repository.save(Product1);
		
		
		List<Product> ProductList = repository.findByExtraTrue();
		Assertions.assertThat(ProductList).isNotNull();
	}
	
	@Test
	public void testfindByLobbyTrue() {
		Product Product1 = new Product(99);
		Product1.setLobby(true);
		repository.save(Product1);
		
		
		List<Product> ProductList = repository.findByLobbyTrue();
		Assertions.assertThat(ProductList).isNotNull();
	}
	
	@Test
	public void findBySubscriptionFalseAndWashTrue() {
		Product Product1 = new Product(99);
		Product1.setSubscription(false);
		Product1.setWash(true);
		repository.save(Product1);
		
		
		List<Product> ProductList = repository.findBySubscriptionFalseAndWashTrue();
		Assertions.assertThat(ProductList).isNotNull();
	}
	
	@Test
	public void testExistsById() {
		Product Product1 = new Product(99);
		repository.save(Product1);
		Assertions.assertThat(repository.existsById(99)).isTrue();
	}
	
}
