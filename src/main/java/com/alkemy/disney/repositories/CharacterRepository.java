package com.alkemy.disney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.disney.entities.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

}
