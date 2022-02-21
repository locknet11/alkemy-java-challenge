package com.alkemy.disney.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.disney.entities.Movie;
import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.interfaces.MovieInterface;
import com.alkemy.disney.models.MovieDTO;
import com.alkemy.disney.models.MovieList;
import com.alkemy.disney.repositories.MovieRepository;
import com.alkemy.disney.validations.MovieValidation;

@Service
public class MovieService implements MovieInterface {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MovieValidation movieValidation;
	
	public Movie dtoToEntity(MovieDTO movieDto) {
		return modelMapper.map(movieDto, Movie.class);
	}
	
	public MovieDTO entityToDto(Movie movie) {
		return modelMapper.map(movie, MovieDTO.class);
	}
	
	@Override
	public List<MovieList> entityToList(List<Movie> movies) {
		
		List<MovieList> movieList = new ArrayList<>();
		
		for(Movie movie : movies) {
			MovieList mList = new MovieList();
			mList.setTitle(movie.getTitle());
			mList.setImg(movie.getImg());
			mList.setCreationDate(movie.getCreationDate());
			movieList.add(mList);
		}
		
		return movieList;
	}
	
	@Override
	public List<MovieList> getAll(String title, Integer genreId, String order){
		if(title == null && genreId == null && order == null) {
			return entityToList(movieRepository.findAll());
		}
		if(title != null) {
			return entityToList(movieRepository.findByTitle(title));
		}
		if(genreId != null) {
			return entityToList(movieRepository.findByGenre(genreId));
		}
		if(order != null && order.equalsIgnoreCase("ASC")) {
			return entityToList(movieRepository.getAllAndOrderASC());
		}
		if(order != null && order.equalsIgnoreCase("DESC")) {
			return entityToList(movieRepository.getAllAndOrderDESC());
		}
		return Collections.emptyList();
	}


	@Override
	public void createNew(MovieDTO movieDto) throws ServiceException {
		movieValidation.validate(movieDto);
		Movie movie = dtoToEntity(movieDto);
		movieRepository.save(movie);
	}

	@Override
	public MovieDTO getById(Integer id) throws ServiceException {
		Optional<Movie> thisMovie = movieRepository.findById(id);
		if(thisMovie.isPresent()) {
			return entityToDto(thisMovie.get());
		}else {
			throw new ServiceException("Movie not found");
		}
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		Optional<Movie> thisMovie = movieRepository.findById(id);
		if(thisMovie.isPresent()) {
			movieRepository.delete(thisMovie.get());
		}else {
			throw new ServiceException("Movie not found");
		}
		
	}

	@Override
	public MovieDTO update(Integer id, MovieDTO movieDTO) throws ServiceException {
		Optional<Movie> thisMovie = movieRepository.findById(id);
		if(thisMovie.isPresent()) {
			Movie toUpdate = thisMovie.get();
			toUpdate = dtoToEntity(movieDTO);
			toUpdate.setId(id);
			movieRepository.save(toUpdate);
			return getById(id);
		}else {
			throw new ServiceException("Movie not found");
		}
	}

}
