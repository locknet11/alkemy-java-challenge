package com.alkemy.disney.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.alkemy.disney.entities.User;
import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.interfaces.UserInterface;
import com.alkemy.disney.models.UserDTO;
import com.alkemy.disney.repositories.UserRepository;
import com.alkemy.disney.validations.UserValidation;

@Service
public class UserService implements UserInterface, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserValidation userValidation;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private UserDTO entityToDto(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	private User dtoToEntity(UserDTO user) {
		return modelMapper.map(user, User.class);
	}

	@Override
	public void signup(String email, String password) throws ServiceException {
		userValidation.validateInputs(email, password);
		userValidation.validateIfUserExists(email);
		User user = new User();
		user.setEmail(email);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		user.setAccountCreation(LocalDateTime.now());
		userRepository.save(user);
	}

	@Override
	public UserDTO getAccountInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> thisUser = userRepository.findByEmail(email);
		if(thisUser.isPresent()) {
			GrantedAuthority role = new SimpleGrantedAuthority("ROLE_USER");
			ArrayList<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(role);
			return new org.springframework.security.core.userdetails.User(thisUser.get().getEmail(), thisUser.get().getPassword() , authorities);
		}else {
			throw new UsernameNotFoundException("User not found");
		}
	}

}
