package com.sds.demo.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sds.demo.exception.CustomerErrorResponse;
import com.sds.demo.exception.CustomerNotFoundException;

@ControllerAdvice
public class CustomerExceptionHandler {
	
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exe){
		
		
		CustomerErrorResponse error=new CustomerErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exe.getMessage());
		error.setTimeStamp(LocalDateTime.now().toString());
		
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);		
		
	}
	
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleGenericException(Exception exe){
		
		
		CustomerErrorResponse error=new CustomerErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exe.getMessage());
		error.setTimeStamp(LocalDateTime.now().toString());
		
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);		
		
	}

}
