package com.spring.springboot.dto;

import java.io.Serializable;

public class ExamsIdDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private StudentDto student;
	private CourseDto course;
	
	public ExamsIdDto() {}
	
	public ExamsIdDto(StudentDto student, CourseDto course) {
		this.student = student;
		this.course = course;
	}

	public StudentDto getStudent() {
		return student;
	}
	public void setStudent(StudentDto student) {
		this.student = student;
	}
	public CourseDto getCourse() {
		return course;
	}
	public void setCourse(CourseDto course) {
		this.course = course;
	}
	
}
