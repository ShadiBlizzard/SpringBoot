package com.spring.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.springboot.exceptions.ApiError;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.ExamAlreadyRegisteredException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorString = "Malformed JSON request";
		logger.info(ex.getStackTrace().toString());
		
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorString));
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler(ObjNotFoundException.class)
	private ResponseEntity<Object> handleEntityNotFound(ObjNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMsg(ex.getMessage());
		logger.info(ex.getStackTrace().toString());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(InvalidOperationException.class)
	private ResponseEntity<Object> handleInvalidOperation(InvalidOperationException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		logger.info(ex.getStackTrace().toString());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(EmptyListException.class)
	private ResponseEntity<Object> handleEmptyList(EmptyListException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		logger.info(ex.getStackTrace().toString());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(ExamAlreadyRegisteredException.class)
	private ResponseEntity<Object> handleExamAlreadyRegistered(ExamAlreadyRegisteredException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		logger.info(ex.getStackTrace().toString());
		return buildResponseEntity(apiError);
	}
	
	

}
