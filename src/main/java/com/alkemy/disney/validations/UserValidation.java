package com.alkemy.disney.validations;

import java.util.Optional;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alkemy.disney.entities.User;
import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.repositories.UserRepository;

@Component
public class UserValidation {

	@Autowired
	private UserRepository userRepository;
	
	public void validateIfUserExists(String email) throws ServiceException{
		Optional<User> thisUser = userRepository.findByEmail(email);
		if(thisUser.isPresent()) {
			throw new ServiceException("This e-mail is already registered");
		}
	}
	
	public void validateInputs(String email, String password) throws ServiceException{
		
		EmailValidator ev = new EmailValidator();
		
		if(email == null || email.isEmpty()) {
			throw new ServiceException("E-mail cannot be null or empty");
		}
		
		if(!ev.isValid(email, null)) {
			throw new ServiceException("The e-mail you provided is not valid");
		}
		
		if(password == null || password.isEmpty()) {
			throw new ServiceException("Password cannot be null or empty");
		}
		
	}
}
