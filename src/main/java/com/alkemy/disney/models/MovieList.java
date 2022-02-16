package com.alkemy.disney.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MovieList {

	private String img;
	private String title;
	private LocalDate creationDate;
}
