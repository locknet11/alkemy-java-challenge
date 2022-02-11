package com.alkemy.disney.models;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDTO {
	
	private String email;
	private LocalDateTime accountCreation;
}
