package com.alkemy.disney.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.disney.exceptions.ExceptionHandler;
import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.models.CharacterDTO;
import com.alkemy.disney.models.CharacterList;
import com.alkemy.disney.services.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController{
	
	@Autowired
	private CharacterService charService;
	
	@GetMapping
	public ResponseEntity<List<CharacterList>> getAllCharacters(){
		return ResponseEntity.ok(charService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<?> createNewCharacter(@RequestBody CharacterDTO characterDto) {
		try {
			charService.createNew(characterDto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}catch(ServiceException e) {
			return ExceptionHandler.throwError(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		try {
			CharacterDTO character = charService.getById(id);
			return ResponseEntity.ok(character);
		}catch(ServiceException e) {
			return ExceptionHandler.throwError(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CharacterDTO characterDto) {
		try {
			CharacterDTO character = charService.update(id, characterDto);
			return ResponseEntity.ok(character);
		}catch(ServiceException e) {
			return ExceptionHandler.throwError(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			charService.delete(id);
			return ResponseEntity.ok().build();
		}catch(ServiceException e) {
			return ExceptionHandler.throwError(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
