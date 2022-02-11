package com.alkemy.disney.interfaces;

import com.alkemy.disney.exceptions.ServiceException;
import com.alkemy.disney.models.UserDTO;

public interface UserInterface{
	
	public void signup(String email, String password) throws ServiceException;
	public UserDTO getAccountInfo();

}
