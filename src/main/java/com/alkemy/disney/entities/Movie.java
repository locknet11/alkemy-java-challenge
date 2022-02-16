package com.alkemy.disney.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Table(name = "movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	private String img;
	
	private LocalDate creationDate;
	
	private Integer rate;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "movies", targetEntity = Character.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Character> characters = new ArrayList<>();

	
	@ManyToMany(cascade = {CascadeType.ALL, CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "movie_genres",joinColumns = @JoinColumn(name = "movie_id"),inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private List<Genre> genres = new ArrayList<>();

}
