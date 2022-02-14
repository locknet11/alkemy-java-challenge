package com.alkemy.disney.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.disney.entities.Character;
import com.alkemy.disney.repositories.CharacterRepository;

@Service
public class CharacterService {
	
	@Autowired
	private CharacterRepository charRepository;
	
	public List<Character> getAll(){
		return charRepository.findAll();
	}

}
