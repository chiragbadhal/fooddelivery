package com.learning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import com.learning.dto.Login;
import com.learning.exception.IdNotFoundException;
import com.learning.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	 private com.learning.repository.LoginRepository repository; 
	
	
	@Override
	public String authenticateUser(Login login) {
		
		if(repository.existsByEmail(login.getEmail()))
		{
			return "User Authenticated";
		}
		// TODO Auto-generated method stub
		else
		{
			return "Invalid User";
		}
	}

	@Override
	public String addCredentials(Login login) {
		// TODO Auto-generated method stub
		Login login2 = repository.save(login);
		if (login2 != null) {
			return "success";
		} else {
			return "fail";
		}
	}
	@Override
	public String deleteCredentials(String email) {
		// TODO Auto-generated method stub
		
		Optional<Login> optional;
		try {
			optional = repository.findByEmail(email);
			if(optional.isEmpty()) {
				throw new IdNotFoundException("record not found");
			}
			else {
				repository.deleteById(email);
				return "register record deleted";
			}
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}

}
