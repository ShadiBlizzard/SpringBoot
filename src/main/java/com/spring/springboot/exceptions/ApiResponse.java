package com.spring.springboot.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse {
	
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	@JsonProperty(required = false)
	private String msg;
	@JsonProperty(required = false)
	private Object response;
	
	private ApiResponse() {
		timestamp = LocalDateTime.now();
	}
	
	public ApiResponse(HttpStatus status) {
		this();
		this.status = status;
	}
	
	public ApiResponse(HttpStatus status, String message) {
		this();
		this.status = status;
		this.msg = message;
	}
	
	public ApiResponse(HttpStatus status, Object object) {
		this();
		this.response = object;
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMsg() {
		return msg;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	
	
	
	

}
