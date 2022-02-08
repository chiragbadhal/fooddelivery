package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Login;
import com.learning.dto.Register;
import com.learning.exception.IdNotFoundException;
import com.learning.service.LoginService;
import com.learning.service.impl.UserServiceImpl;

@RestController
@RequestMapping
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	@Autowired
	LoginService loginservice;
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody Register register) throws Exception{
		Register result = userService.addUser(register);
		return ResponseEntity.status(201).body(result);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> addUser(@Valid @RequestBody Login login) throws Exception{
		String result = loginservice.authenticateUser(login);
		Map<String,String> map = new HashMap<String,String>();
		map.put("message", result);
		if(result.equals("success")) {
			return ResponseEntity.status(200).body(map);
		}
		return ResponseEntity.status(403).body(map);
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getUsers(){
		List<Register> result = userService.getUsers();
		return ResponseEntity.status(200).body(result);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") String id) throws IdNotFoundException{
		Register result = userService.getUserById(id);
		return ResponseEntity.ok(result);	
		
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<?> UpdateUser(@PathVariable("id") String id,@RequestBody Register register)throws IdNotFoundException{
		Register result = userService.updateUser(id, register);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> DeleteUser(@PathVariable("id") String id) throws IdNotFoundException{
		String result = userService.deleteUserById(id);
		Map<String,String> map = new HashMap<String,String>();
		map.put("message", result);
		return ResponseEntity.status(200).body(map);
	}
}
