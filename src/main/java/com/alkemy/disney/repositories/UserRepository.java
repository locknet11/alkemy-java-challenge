package com.alkemy.disney.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.disney.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

	public Optional<User> findByEmail(String email);
}
