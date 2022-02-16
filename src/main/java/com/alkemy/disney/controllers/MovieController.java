package com.alkemy.disney.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.disney.exceptions.ExceptionHandler;
import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.models.MovieDTO;
import com.alkemy.disney.models.MovieList;
import com.alkemy.disney.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping
	public List<MovieList> getAll(){
		return movieService.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> createNew(@RequestBody MovieDTO movieDto){
		try {
			movieService.createNew(movieDto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}catch(ServiceException e) {
			return ExceptionHandler.throwError(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		try {
			MovieDTO movie = movieService.getById(id);
			return ResponseEntity.ok(movie);
		}catch (ServiceException e) {
			return ExceptionHandler.throwError(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
