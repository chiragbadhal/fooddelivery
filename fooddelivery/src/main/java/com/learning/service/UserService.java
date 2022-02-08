package com.learning.service;

import java.util.Optional;

import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidEmailException;
import com.learning.exception.InvalidIdLengthException;
import com.learning.exception.InvalidNameException;
import com.learning.exception.InvalidPasswordException;

import java.util.ArrayList;
import java.util.List;
import com.learning.repo.UserRepository;

public interface UserService {
	public Register addUser(Register register) throws AlreadyExistsException;
	public List<Register> getUsers();
	public Register updateUser(String id, Register register) throws IdNotFoundException;
	public Register getUserById(String id) throws IdNotFoundException;
	public String deleteUserById(String id) throws IdNotFoundException;
}