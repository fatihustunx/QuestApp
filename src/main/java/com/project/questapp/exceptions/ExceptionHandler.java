package com.project.questapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleUserNotFound() {
		System.out.println("hello");
	}
}
