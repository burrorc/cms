package com.yourautospa.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourautospa.cms.dao.SubscriptionRepository;
import com.yourautospa.cms.entity.Subscription;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	private SubscriptionRepository subscriptionRepository;

	@Autowired
	public SubscriptionServiceImpl(SubscriptionRepository theSubscriptionRepository) {
		subscriptionRepository = theSubscriptionRepository;
	}

	@Override
	public List<Subscription> findAll() {
		return subscriptionRepository.findAll();
	}
//	@Override
//	public List<Subscription> findAll() {
//		return subscriptionRepository.findAll();
//	}

	@Override
	public Subscription findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Subscription> result = subscriptionRepository.findById(theId);

		Subscription theSubscription = null;

		if (result.isPresent()) {
			theSubscription = result.get();
		} else {
			throw new RuntimeException("Didn't find subscription with plate - " + theId);
		}

		return theSubscription;
	}

	@Override
	public void save(Subscription theSubscription) {
//		if(theSubscription.getMake()=="") {
//			theSubscription.setMake("UNKNOWN");
//		}
//		if(theSubscription.getModel()=="") {
//			theSubscription.setModel("UNKNOWN");
//		}
		subscriptionRepository.save(theSubscription);

	}

	@Override
	public void deleteById(int theId) {
		subscriptionRepository.deleteById(theId);

	}

//	@Override
//	public Subscription findOrAdd(String theId) {
//		Optional<Subscription> result = subscriptionRepository.findById(theId);
//
//		Subscription theSubscription = null;
//
//		if (result.isPresent()) {
//			theSubscription = result.get();
//		} else {
//			theSubscription = new Subscription(theId);
//		}
//
//		return theSubscription;
//	}

}
