package com.alkemy.disney.interfaces;

import java.util.List;

import com.alkemy.disney.entities.Character;
import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.models.CharacterDTO;
import com.alkemy.disney.models.CharacterList;

public interface CharacterInterface {
	
	public void createNew(CharacterDTO character) throws ServiceException;
	public List<CharacterList> entityToList(List<Character> characters);
	public List<CharacterList> getAll();
	public CharacterDTO getById(Integer id) throws ServiceException;

}
