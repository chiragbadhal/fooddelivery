package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Login;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidEmailException;
import com.learning.repo.LoginRepository;
import com.learning.service.LoginService;
//import com.learning.dto.EROLE;

@Service
public class LoginServiceImpl implements LoginService {


	@Autowired
	private LoginRepository repository ;
	
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
	public String deleteCredentials(String userName) {
		// TODO Auto-generated method stub
		
		Optional<Login> optional;
		try {
			optional = repository.findById(userName);
			if(optional.isEmpty()) {
				throw new IdNotFoundException("record not found");
			}
			else {
				repository.deleteById(userName);
				return "login record deleted";
			}
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	public String changePassword(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public String changeRole(String userName, EROLE role) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = InvalidEmailException.class)
	public String authenticateUser(Login login) throws InvalidEmailException{
		if(!repository.existsByUserName(login.getUserName())) {
			return "user does not exist";
		}
		List<Login> userList = repository.findAll();
		Login myuser = null;
		for(Login user:userList) {
			if(user.getUserName().equals(login.getUserName())) {
				myuser = user;
				break;
			}
		}
		if(myuser.getPassword().equals(login.getPassword())) {
			return "success";
		}
		return "fail";
	}

}