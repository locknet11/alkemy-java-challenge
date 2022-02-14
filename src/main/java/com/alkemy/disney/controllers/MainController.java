package com.alkemy.disney.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
	
	@GetMapping
	public ResponseEntity<?> welcome(){
		Map<String, String> response = new HashMap<>();
		response.put("help", "To check API documentation go to http://localhost:8080/api/docs");
		return ResponseEntity.ok(response);
	}
}
