package com.alkemy.disney.models;

import java.time.LocalDate;
import java.util.List;

import com.alkemy.disney.entities.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class MovieDTO {
	
	private Integer id;
	private String title;
	private String img;
	private LocalDate creationDate;
	private Integer rate;
	@JsonIgnoreProperties({"movies", "id"})
	private List<Genre> genres;
	@JsonIgnoreProperties("movies")
	private List<CharacterDTO> characters;

}
