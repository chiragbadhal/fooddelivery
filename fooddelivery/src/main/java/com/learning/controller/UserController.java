package com.learning.controller;



import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.EROLE;
import com.learning.dto.Role;
import com.learning.dto.User;
import com.learning.exception.IdNotFoundException;
import com.learning.payload.request.LoginRequest;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.payload.response.MessageResponse;
import com.learning.repository.UserRepository;
import com.learning.security.jwt.JwtUtils;
import com.learning.security.services.UserDetailsImpl;
import com.learning.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private com.learning.repository.RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
						loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetailsImpl.getAuthorities()
				.stream()
				.map(i->i.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetailsImpl.getId(),
				userDetailsImpl.getUsername(),
				userDetailsImpl.getEmail(),
				userDetailsImpl.getAddress(),
				roles
			  ));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
		if (userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		User user = new User(signupRequest.getUsername(),
				signupRequest.getEmail(),
				passwordEncoder.encode(signupRequest.getPassword()),
				signupRequest.getAddress());
		
		Set<String> strRoles = signupRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		user.setRoles(roles);
				
		
	
		userRepository.save(user);
		return ResponseEntity
				.status(201)
				.body(new MessageResponse("User created successfully"));
	}
	
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUsersById(@PathVariable("userId") Long id) throws IdNotFoundException{
		
		User result = userService.getUserById(id);
		return ResponseEntity.status(201).body(result);
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(){
		Optional<List<User>> optional = userService.getAllUsers();
		
		if(optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "no records found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
		
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody Map<String,String> map) throws IdNotFoundException{
		User user = new User();
		if(map.get("email")!=null) {
			user.setEmail(map.get("email"));
		}
		if(map.get("username")!=null) {
			user.setEmail(map.get("username"));
		}
		if(map.get("password")!=null) {
			user.setEmail(map.get("password"));
		}
		if(map.get("address")!=null) {
			user.setEmail(map.get("address"));
		}
		
		
		return new ResponseEntity<>(userService.updateUser(id, user),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) throws IdNotFoundException{
		
		String result = userService.deleteUserById(id);
		
		if(result.equals("success")) {
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
		
	
	
}