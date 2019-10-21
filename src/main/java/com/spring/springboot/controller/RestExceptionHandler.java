package com.spring.springboot.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.ExamAlreadyRegisteredException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.exceptions.UnexistingExamException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorString = "Malformed JSON request";
		logger.info(ex.getMessage(), ex);
		
		return buildResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST, errorString));
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorString = "Malformed JSON request";
		logger.info(ex.getMessage(), ex);
		
		return buildResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST, errorString));
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String errorString = "Some variables are missing";
		logger.info(ex.getMessage(), ex);
		
		return buildResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST, errorString));
	}
	

	
	@ExceptionHandler(RestClientResponseException.class)
	public final ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
		String errorString = "Some errors occurred";
		logger.info(ex.getMessage(), ex);
		
		return buildResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST, errorString));
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String errorString = "Some variables are with wrong typing";
		logger.info(ex.getMessage(), ex);
		
		return buildResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST, errorString));
	}

	
	private ResponseEntity<Object> buildResponseEntity(ApiResponse apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler(ObjNotFoundException.class)
	private ResponseEntity<Object> handleEntityNotFound(ObjNotFoundException ex) {
		ApiResponse apiError = new ApiResponse(HttpStatus.NOT_FOUND);
		apiError.setMsg(ex.getMessage());
		logger.info(ex.getMessage(), ex);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(EmptyListException.class)
	private ResponseEntity<Object> handleEmptyList(EmptyListException ex) {
		ApiResponse apiError = new ApiResponse(HttpStatus.NOT_FOUND);
		apiError.setMsg(ex.getMessage());
		logger.info(ex.getMessage(), ex);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler({ExamAlreadyRegisteredException.class, UnexistingExamException.class, InvalidOperationException.class,
		DataAccessException.class})
	private ResponseEntity<Object> handleExamAlreadyRegistered(ExamAlreadyRegisteredException ex) {
		ApiResponse apiError = new ApiResponse(HttpStatus.BAD_REQUEST);
		apiError.setMsg(ex.getMessage());
		logger.info(ex.getMessage(), ex);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(PropertyReferenceException.class)
	private ResponseEntity<Object> handlePropertyReference(PropertyReferenceException ex) {
		ApiResponse apiError = new ApiResponse(HttpStatus.UNPROCESSABLE_ENTITY);
		apiError.setMsg(ex.getMessage());
		logger.info(ex.getMessage(), ex);
		return buildResponseEntity(apiError);
	}
	
}
