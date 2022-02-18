package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Login;
import com.learning.dto.User;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.repository.LoginRepository;
import com.learning.repository.UserRepository;
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
	@org.springframework.transaction.annotation.Transactional(rollbackFor=AlreadyExistsException.class)
	public User addUser(User register) throws AlreadyExistsException {
		// TODO Auto-generated method stub
	
		if(repository.existsByEmail(register.getEmail()) == true) {
			throw new AlreadyExistsException("record already exists");
		}
		User register2 = repository.save(register);
		if (register2 != null) {
			Login login = new Login(register.getEmail(), register.getPassword());
			if(loginRepository.existsByEmail(register2.getEmail())) {
				throw new AlreadyExistsException("login already exists");
			}
			
			String result = service.addCredentials(login);
			if(result == "success") {
					return register2;
			}
			else
			{
				return null;
			}
		}	
		else {
			return null;
		}
				
	}

	@Override
	public User updateUser(Long id, User register) throws IdNotFoundException{
		boolean status = repository.existsById(id);
		if(!status) {
			throw new IdNotFoundException("User with this Id does not exist");
		}
		User user = repository.getById(id);
		user.setAddress(register.getAddress());
		user.setEmail(register.getEmail());
		user.setUsername(register.getUsername());
		user.setPassword(register.getPassword());
		User newregister = repository.save(user);
		return newregister;
	}

	@Override
	public User getUserById(Long id) throws IdNotFoundException {
		// TODO Auto-generated method stub
	   Optional<User>optional=repository.findById(id);
	   if(optional.isEmpty())
	   {
		   throw new IdNotFoundException("id not exist");
	   }
	   else
	   {
		return optional.get() ;
	   }
	}

	@Override
	public Optional<List<User>> getAllUsers()
			 {
		// TODO Auto-generated method stub
		List<User> list = repository.findAll();
		User[] array = new User[list.size()];
		return Optional.ofNullable(repository.findAll());
		
		
	}

	@Override
	public String deleteUserById(Long id) throws IdNotFoundException {
		
			User optional;
			try {
				optional = this.getUserById(id);
				if(optional==null) {
					throw new IdNotFoundException("record not found");
				}
				else {
					repository.deleteById(id);
					return "record deleted";
				}
			} catch (IdNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new IdNotFoundException(e.getMessage());
			}
		
	}
}


