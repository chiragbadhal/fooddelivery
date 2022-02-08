package com.learning.service;

import com.learning.dto.Login;
import com.learning.exception.InvalidEmailException;


public interface LoginService {
	public String addCredentials(Login login);
	public String deleteCredentials(String userName);
	public String changePassword(String userName, String password);
	public String authenticateUser(Login login) throws InvalidEmailException;

	

}