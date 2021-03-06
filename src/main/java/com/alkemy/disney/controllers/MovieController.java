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
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<MovieList> getAll(
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "genreId", required = false) Integer genreId,
			@RequestParam(name = "order", required = false) String order){
		return movieService.getAll(title, genreId, order);
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
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody MovieDTO movieDto){ 
		try {
			MovieDTO movie = movieService.update(id, movieDto);
			return ResponseEntity.ok(movie);
		}catch (ServiceException e) {
			return ExceptionHandler.throwError(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			movieService.delete(id);
			return ResponseEntity.ok().build();
		}catch (ServiceException e) {
			return ExceptionHandler.throwError(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
