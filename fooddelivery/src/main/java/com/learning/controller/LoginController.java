package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Login;
import com.learning.exception.AlreadyExistsException;
import com.learning.service.LoginService;

@RequestMapping("/login")
@RestController
public class LoginController
{
	
	@Autowired
	LoginService loginService;
	@PostMapping("/authenticateUser")
	public ResponseEntity<?> authenticateUser(@RequestBody Login login)  {
		
		String result = loginService.authenticateUser(login);
		
		return ResponseEntity.status(200).body(result);
			
	}

}
