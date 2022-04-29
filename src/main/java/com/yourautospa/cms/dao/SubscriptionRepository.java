package com.yourautospa.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourautospa.cms.entity.Subscription;


public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

}
