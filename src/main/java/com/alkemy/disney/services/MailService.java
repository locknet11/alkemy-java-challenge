package com.alkemy.disney.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaSender;
	
	@Async
	public void sendWelcomeEmail(String email) {
		SimpleMailMessage mail = new SimpleMailMessage();
		String subject = "Welcome to Disney API";
		String body = "Welcome" + email + ", thanks for signing up in Disney API \n ,"
				+ "You can check the API Docs here: http://localhost:8080/swagger-ui.html";
		
		mail.setFrom("disney@api.com");
		mail.setTo(email);
		mail.setSubject(subject);
		mail.setText(body);
		javaSender.send(mail);
		
	}
	

}
