package com.alkemy.disney.validations;

import org.springframework.stereotype.Component;

import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.models.CharacterDTO;

@Component
public class CharacterValidation {
	
	public void validateCreation(CharacterDTO character) throws ServiceException {
		
		if(character.getName() == null || character.getName().isEmpty()) {
			throw new ServiceException("Name cannot be null or empty");
		}
		
		if(character.getImg() == null || character.getImg().isEmpty()) {
			throw new ServiceException("Image cannot be null or empty");
		}
		
		if(character.getStory() == null || character.getStory().isEmpty()) {
			throw new ServiceException("Story cannot be null or empty");
		}
		
		if(character.getAge() == null) {
			throw new ServiceException("Age cannot be null");
		}
		
		if(character.getWeight() == null) {
			throw new ServiceException("Weight cannot be null");
		}
	}

}
