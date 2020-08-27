package com.webservices.playground.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * The annotation is necessary to throw exception with the particular HTTP status code.
 * But since we have a Global Exception controller. It is not necessary because we have 
 * already defined in the Global exception controller 
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String errorMessage){
		super(errorMessage);
	}
}
