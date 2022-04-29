package com.yourautospa.cms.service;

import java.util.List;

import com.yourautospa.cms.entity.Wash;

public interface WashService {

	public List<Wash> findAll();

	public Wash findById(int theId);

	public void save(Wash theWash);

	public void deleteById(int theId);

	//public Subscription findOrAdd(String theId);
}
