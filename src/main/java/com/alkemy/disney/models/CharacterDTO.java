package com.alkemy.disney.models;

import java.util.List;

import com.alkemy.disney.entities.Movie;

import lombok.Data;

@Data
public class CharacterDTO {
	
	private Integer id;
	
	private String name;
	
	private String img;
	
	private Integer age;
	
	private Double weight;
	
	private String story;
	
	private List<Movie> movies;

}
