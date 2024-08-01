package com.example.customer.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<?> validationExcpetionHandler(MethodArgumentNotValidException ex, WebRequest request) {
	        ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), request.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
