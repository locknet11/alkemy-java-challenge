package com.alkemy.disney.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkemy.disney.entities.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
	
	@Query(value="SELECT * FROM characters c WHERE c.name LIKE %:name%", nativeQuery=true)
	public List<Character> findByName(@Param("name") String name);
	
	@Query(value="SELECT * FROM characters c WHERE c.age = :age", nativeQuery=true)
	public List<Character> findByAge(@Param("age") Integer age);
	
	@Query(value="SELECT c.* FROM characters c, chars_movie cm WHERE cm.char_id = c.id AND cm.movie_id = :movieId", nativeQuery=true)
	public List<Character> findByMovieId(@Param("movieId") Integer movieId);

}
