package com.yourautospa.cms.service;

import java.util.List;

import com.yourautospa.cms.entity.Product;


public interface ProductService {

	public List<Product> findAll();

	public Product findById(int theId);

	public void save(Product theProduct);

	public void deleteById(int theId);

	public List<Product> findBySubscriptionTrue();

	public List<Product> findByWashTrue();

	public List<Product> findByExtraTrue();

	public List<Product> findByLobbyTrue();

	public List<Product> findBySubscriptionFalseAndWashTrue();

	// public Subscription findOrAdd(String theId);
}
