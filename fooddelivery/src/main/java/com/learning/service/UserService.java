package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.dto.User;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;


@Service
public interface UserService 

{
	public User addUser(User register) throws AlreadyExistsException;
	public User updateUser(Long id, User register) throws IdNotFoundException;
	public User getUserById(Long id) throws IdNotFoundException ;
	public Optional<List<User>> getAllUsers();
	public String deleteUserById(Long id) throws IdNotFoundException;
	

}
