package com.alkemy.disney.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "characters")
public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String name;
	
	private String img;
	
	private Integer age;
	
	private Double weight;
	
	private String story;
	
	 @ManyToMany(mappedBy = "characters", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 private List<Movie> movies = new ArrayList<>();
}
