package com.alkemy.disney.validations;

import org.springframework.stereotype.Component;

import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.models.MovieDTO;

@Component
public class MovieValidation {

	public void validate(MovieDTO movieDto) throws ServiceException {
		
		if(movieDto.getTitle() == null || movieDto.getTitle().isEmpty()) {
			throw new ServiceException("Title cannot be null or empty");
		}
		
		if(movieDto.getCreationDate() == null) {
			throw new ServiceException("Creation date cannot be null");
		}
		
		if(movieDto.getImg() == null || movieDto.getImg().isEmpty()) {
			throw new ServiceException("Image cannot be null or empty");
		}
		
		if(movieDto.getRate() == null) {
			throw new ServiceException("Rating cannot be null");
		}
		
		if(movieDto.getRate() <= 0 || movieDto.getRate() > 5) {
			throw new ServiceException("Rate field accepts 1 to 5 values only");
		}
		
	}
}
