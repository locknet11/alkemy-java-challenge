package com.alkemy.disney.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionHandler {
	
	public static ResponseEntity<?> throwError(HttpStatus status, String message) {
		Map<String, String> body = new HashMap<>();
		body.put("status", status.name());
		body.put("code", String.valueOf(status.value()));
		body.put("message", message);
		return ResponseEntity.status(status).body(body);
	}

}
