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
@Table(name = "genres")
public class Genre {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String img;

	    @ManyToMany(mappedBy = "genres",fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	    private List<Movie> movies = new ArrayList<>();

}
