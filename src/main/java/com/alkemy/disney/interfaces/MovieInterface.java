package com.alkemy.disney.interfaces;

import java.util.List;

import com.alkemy.disney.entities.Movie;
import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.models.MovieDTO;
import com.alkemy.disney.models.MovieList;

public interface MovieInterface {

	public List<MovieList> entityToList(List<Movie> movies);
	public List<MovieList> getAll();
	public void createNew(MovieDTO movieDto) throws ServiceException;
	public MovieDTO getById(Integer id) throws ServiceException;
	public void delete(Integer id) throws ServiceException;
	public MovieDTO update(Integer id, MovieDTO movieDTO) throws ServiceException;
}
