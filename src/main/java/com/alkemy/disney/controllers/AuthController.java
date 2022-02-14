package com.alkemy.disney.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.models.AuthRequest;
import com.alkemy.disney.models.AuthResponse;
import com.alkemy.disney.services.UserService;
import com.alkemy.disney.utils.JwtUtil;

@RestController
@RequestMapping("/auth/")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwt;
	
	@Autowired
	private AuthenticationManager authManager;
	
	private Map<String, String> returnError(String e){
		Map<String, String> message = new HashMap<>();
		message.put("error", e);
		return message;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> signup(@RequestBody AuthRequest request){
		
		try {
			userService.signup(request.getEmail(), request.getPassword());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch(ServiceException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(returnError(e.getMessage()));
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody AuthRequest request){
		
		try {
			
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		authManager.authenticate(auth);
		UserDetails user = userService.loadUserByUsername(request.getEmail());
		String token = jwt.generateToken(user);
		AuthResponse response = new AuthResponse();
		response.setToken(token);
		return new ResponseEntity<AuthResponse>(response, HttpStatus.OK);
		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(returnError(e.getMessage()));
		}
	}

}
