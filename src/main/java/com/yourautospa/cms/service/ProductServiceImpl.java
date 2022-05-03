package com.yourautospa.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourautospa.cms.dao.ProductRepository;
import com.yourautospa.cms.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository theProductRepository) {
		productRepository = theProductRepository;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Product> result = productRepository.findById(theId);

		Product theProduct = null;

		if (result.isPresent()) {
			theProduct = result.get();
		} else {
			throw new RuntimeException("Didn't find product with id - " + theId);
		}

		return theProduct;
	}

	@Override
	public void save(Product theProduct) {
		
		productRepository.save(theProduct);

	}

	@Override
	public void deleteById(int theId) {
		productRepository.deleteById(theId);

	}

	@Override
	public List<Product> findBySubscriptionTrue() {
		// TODO Auto-generated method stub
		return productRepository.findBySubscriptionTrue();
	}

	@Override
	public List<Product> findByWashTrue() {
		// TODO Auto-generated method stub
		return productRepository.findByWashTrue();
	}

	@Override
	public List<Product> findByExtraTrue() {
		// TODO Auto-generated method stub
		return productRepository.findByExtraTrue();
	}

	@Override
	public List<Product> findByLobbyTrue() {
		// TODO Auto-generated method stub
		return productRepository.findByLobbyTrue();
	}

	@Override
	public List<Product> findBySubscriptionFalseAndWashTrue() {
		// TODO Auto-generated method stub
		return productRepository.findBySubscriptionFalseAndWashTrue();
	}

	@Override
	public List<Product> findBySubscriptionFalseAndWashTrueOrExtraTrue() {
		// TODO Auto-generated method stub
		return productRepository.findBySubscriptionFalseAndWashTrueOrExtraTrue();
	}

	@Override
	public boolean existsById(int theId) {
		// TODO Auto-generated method stub
		return productRepository.existsById(theId);
	}

	

	

	

//	@Override
//	public Product findOrAdd(String theId) {
//		Optional<Product> result = productRepository.findById(theId);
//
//		Product theProduct = null;
//
//		if (result.isPresent()) {
//			theProduct = result.get();
//		} else {
//			theProduct = new Product(theId);
//		}
//
//		return theProduct;
//	}

	
}
