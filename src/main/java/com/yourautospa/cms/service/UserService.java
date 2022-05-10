package com.yourautospa.cms.service;

import java.util.List;

import com.yourautospa.cms.entity.User;

public interface UserService {
	
public List<User> findAll();
	
	public User findById(int theId);
	
	public void save(User theUser);
	
	public void deleteById(int theId);

}
