package com.spring.Ecommerce2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.Ecommerce2.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    
}
