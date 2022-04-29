package com.yourautospa.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourautospa.cms.dao.WashRepository;
import com.yourautospa.cms.entity.Wash;

@Service
public class WashServiceImpl implements WashService {

	private WashRepository washRepository;

	@Autowired
	public WashServiceImpl(WashRepository theWashRepository) {
		washRepository = theWashRepository;
	}

	@Override
	public List<Wash> findAll() {
		return washRepository.findAll();
	}

	@Override
	public Wash findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Wash> result = washRepository.findById(theId);

		Wash theWash = null;

		if (result.isPresent()) {
			theWash = result.get();
		} else {
			throw new RuntimeException("Didn't find wash with id - " + theId);
		}

		return theWash;
	}

	@Override
	public void save(Wash theWash) {
		
		washRepository.save(theWash);

	}

	@Override
	public void deleteById(int theId) {
		washRepository.deleteById(theId);

	}

//	@Override
//	public Wash findOrAdd(String theId) {
//		Optional<Wash> result = washRepository.findById(theId);
//
//		Wash theWash = null;
//
//		if (result.isPresent()) {
//			theWash = result.get();
//		} else {
//			theWash = new Wash(theId);
//		}
//
//		return theWash;
//	}

	
}
