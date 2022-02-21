package com.alkemy.disney.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkemy.disney.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	public List<Movie> findByTitle(@Param("title") String title);
	
	@Query(value = "SELECT m.* FROM movies m, movie_genres mg WHERE m.id = mg.movie_id AND mg.genre_id = :genreId", nativeQuery = true)
	public List<Movie> findByGenre(@Param("genreId") Integer genreId);
	
	@Query(value = "SELECT * FROM movies m ORDER BY m.title ASC", nativeQuery = true)
	public List<Movie> getAllAndOrderASC();
	
	@Query(value = "SELECT * FROM movies m ORDER BY m.title DESC", nativeQuery = true)
	public List<Movie> getAllAndOrderDESC();

}
