package com.learning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.dto.User;

public interface UserRepository extends JpaRepository<User,Long> {

Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);

}
