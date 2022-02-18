package com.learning.service;

import org.springframework.stereotype.Service;

import com.learning.dto.Login;

@Service
public interface LoginService {
	
	public String authenticateUser(Login login);
	public String addCredentials(Login login);
	public String deleteCredentials(String email);

}
