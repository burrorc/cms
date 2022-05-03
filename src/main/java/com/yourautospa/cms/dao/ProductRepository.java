package com.yourautospa.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourautospa.cms.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public List<Product> findBySubscriptionTrue();
	
	public List<Product> findByWashTrue();
	
	public List<Product> findByExtraTrue();
	
	public List<Product> findByLobbyTrue();
	
	public List<Product> findBySubscriptionFalseAndWashTrue();
	
	public List<Product> findBySubscriptionFalseAndWashTrueOrExtraTrue();

}
