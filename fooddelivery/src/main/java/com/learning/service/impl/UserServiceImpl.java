package com.learning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Login;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.repo.LoginRepository;
import com.learning.repo.UserRepository;
import com.learning.service.LoginService;
import com.learning.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private LoginService service;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = AlreadyExistsException.class)
	public Register addUser(Register register) throws AlreadyExistsException {
		// TODO Auto-generated method stub
		//make exception for the next line
		boolean status = repository.existsByEmail(register.getEmail()) ;
		if(status) {
			throw new AlreadyExistsException("this record already exists");
		}
		Register register2 = repository.save(register);
		if (register2 != null) {
			Login login = new Login();
			login.setUserName(register.getEmail());
			login.setPassword(register.getPassword());
			login.setRegister(register2);
			if(loginRepository.existsByUserName(register.getEmail())) {
				throw new AlreadyExistsException("this record already exists");
			}
			String result = service.addCredentials(login);
			if(result == "success") {
					//return "record added in register and login";
				return register2;
			}
			else {
				return null;
			}
		}	
		else {
			return null;
		}
				
	}
	
	@Override
	public List<Register> getUsers(){
		List<Register> users = repository.findAll();
		return users;
	}
	
	@Override
	public Register getUserById(String id) throws IdNotFoundException{
		boolean status = repository.existsById(id);
		if(!status) {
			throw new IdNotFoundException("User with this Id does not exist");
		}
		Register user = repository.getById(id);
		return user;
	}
	
	@Override
	public Register updateUser(String id, Register register) throws IdNotFoundException{
		boolean status = repository.existsById(id);
		if(!status) {
			throw new IdNotFoundException("User with this Id does not exist");
		}
		Register user = repository.getById(id);
		user.setAddress(register.getAddress());
		user.setEmail(register.getEmail());
		user.setName(register.getName());
		user.setPassword(register.getPassword());
		Register newregister = repository.save(user);
		return newregister;
	}
	
	@Override
	public String deleteUserById(String id) throws IdNotFoundException{
		System.out.print(id);
		boolean status = repository.existsById(id);
		if(!status) {
			return "Sorry user With" +id+"not found";
		}
		Register register = repository.getById(id);
		List<Login> userList = loginRepository.findAll();
		Login myuser = null;
		for(Login user:userList) {
			if(user.getUserName().equals(register.getEmail())) {
				myuser = user;
				break;
			}
		}
		loginRepository.delete(myuser);
		repository.deleteById(id);
		return "User Deleted Successfully";
	}

}
