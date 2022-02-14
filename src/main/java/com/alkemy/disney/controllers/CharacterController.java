package com.alkemy.disney.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.disney.entities.Character;
import com.alkemy.disney.services.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {
	
	@Autowired
	private CharacterService charService;
	
	@GetMapping
	public ResponseEntity<List<Character>> getAllCharacters(){
		return ResponseEntity.ok(charService.getAll());
	}

}
