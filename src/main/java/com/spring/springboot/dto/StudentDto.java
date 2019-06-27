package com.spring.springboot.dto;

import java.io.Serializable;

public class StudentDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String surname;

	public StudentDto() {}
	
	public StudentDto(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public StudentDto(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
