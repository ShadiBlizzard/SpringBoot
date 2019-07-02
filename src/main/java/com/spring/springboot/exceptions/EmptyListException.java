package com.spring.springboot.exceptions;

public class EmptyListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1474909703283338347L;

	
	public EmptyListException(String msg) {
		super(msg);
	}

}
