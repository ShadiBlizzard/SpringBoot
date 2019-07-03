package com.spring.springboot.exceptions;

public class ExamAlreadyRegisteredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7627192165407709632L;

	
	public ExamAlreadyRegisteredException(String msg) {
		super(msg);
		
	}

}
