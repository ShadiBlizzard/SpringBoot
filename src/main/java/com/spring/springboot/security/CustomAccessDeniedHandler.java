package com.spring.springboot.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.utils.StringUtils;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.info(accessDeniedException.getMessage(), accessDeniedException);
		response.setContentType("application/json");
		ApiResponse error = new ApiResponse(HttpStatus.FORBIDDEN, StringUtils.ACCESS_DENIED);
		OutputStream out = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(error,error.getStatus());
		mapper.findAndRegisterModules();
		mapper.writeValue(out, responseEntity.getBody());
		out.flush();
	}

}
