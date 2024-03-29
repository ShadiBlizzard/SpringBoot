package com.spring.springboot.dto;

import java.io.Serializable;

public class CourseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private String description;

	public CourseDto() {}

	public CourseDto(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
