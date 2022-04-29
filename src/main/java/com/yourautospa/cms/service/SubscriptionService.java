package com.yourautospa.cms.service;

import java.util.List;

import com.yourautospa.cms.entity.Subscription;

public interface SubscriptionService {

	public List<Subscription> findAll();

	public Subscription findById(int theId);

	public void save(Subscription theSubscription);

	public void deleteById(int theId);

	//public Subscription findOrAdd(String theId);
}
