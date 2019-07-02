package com.spring.springboot.exceptions;

public class InvalidOperationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5609009583496073861L;
	
	public InvalidOperationException (String msg) {
		super(msg);
	}

}
