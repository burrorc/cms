package com.yourautospa.cms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourautospa.cms.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
    
}