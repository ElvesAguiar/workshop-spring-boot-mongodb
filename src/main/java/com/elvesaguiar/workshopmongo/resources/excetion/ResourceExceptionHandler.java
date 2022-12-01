package com.elvesaguiar.workshopmongo.resources.excetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.elvesaguiar.workshopmongo.resources.exception.StandardError;
import com.elvesaguiar.workshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request ){
		
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err= new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
