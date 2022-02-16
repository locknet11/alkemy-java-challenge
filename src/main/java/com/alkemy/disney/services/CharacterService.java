package com.alkemy.disney.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.disney.entities.Character;
import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.interfaces.CharacterInterface;
import com.alkemy.disney.models.CharacterDTO;
import com.alkemy.disney.models.CharacterList;
import com.alkemy.disney.repositories.CharacterRepository;
import com.alkemy.disney.validations.CharacterValidation;

@Service
public class CharacterService implements CharacterInterface{
	
	@Autowired
	private CharacterRepository charRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CharacterValidation characterValidation;
	
	public Character dtoToEntity(CharacterDTO characterDto) {
		return modelMapper.map(characterDto, Character.class);
	}
	
	public CharacterDTO entityToDto(Character character) {
		return modelMapper.map(character, CharacterDTO.class);
	}
	
	@Override
	public List<CharacterList> entityToList(List<Character> characters) {
		ArrayList<CharacterList> charactersList = new ArrayList<>();
		
		for (Character c : characters) {
			CharacterList cList = new CharacterList();
			cList.setName(c.getName());
			cList.setImg(c.getImg());
			charactersList.add(cList);
		}
		return charactersList;
	}
	
	@Override
	public void createNew(CharacterDTO characterDto) throws ServiceException{
		characterValidation.validateCreation(characterDto);
		Character character = dtoToEntity(characterDto);
		charRepository.save(character);
	}
	
	@Override
	public List<CharacterList> getAll(){
		return entityToList(charRepository.findAll());
	}

	@Override
	public CharacterDTO getById(Integer id) throws ServiceException {
		Optional<Character> thisChar = charRepository.findById(id);

		if(thisChar.isPresent()) {
			return entityToDto(thisChar.get());
		}else {
			throw new ServiceException("This character doesn't exist.");
		}
	}

	@Override
	public CharacterDTO update(Integer id, CharacterDTO characterDto) throws ServiceException {
		Optional<Character> thisChar = charRepository.findById(id);
		if(thisChar.isPresent()) {
			Character toUpdate = thisChar.get();
			toUpdate = dtoToEntity(characterDto);
			toUpdate.setId(id);
			charRepository.save(toUpdate);
			return getById(id);
		}else {
			throw new ServiceException("Character not found");
		}
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		Optional<Character> thisChar = charRepository.findById(id);
		if(thisChar.isPresent()) {
			charRepository.delete(thisChar.get());
		}else {
			throw new ServiceException("Character not found");
		}
		
	}
	
	
	
	

}
