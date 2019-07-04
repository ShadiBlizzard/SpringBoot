package com.spring.springboot.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiResponse {
	
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String msg;
	
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

	
	
	

}
