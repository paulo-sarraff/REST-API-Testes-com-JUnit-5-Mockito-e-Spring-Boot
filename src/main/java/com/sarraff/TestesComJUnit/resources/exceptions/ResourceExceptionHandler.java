package com.sarraff.TestesComJUnit.resources.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sarraff.TestesComJUnit.services.exceptions.DataIntegratyViolationException;
import com.sarraff.TestesComJUnit.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){
		StandardError error = new StandardError(LocalDate.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegratyViolationException.class)
	public ResponseEntity<StandardError> dataIntegratyViolationException(DataIntegratyViolationException ex, HttpServletRequest request){
		StandardError error = new StandardError(LocalDate.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
