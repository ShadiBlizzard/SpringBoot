package com.spring.springboot.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ExamsId implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Student student;
	private Course course;

	@ManyToOne(cascade = CascadeType.REMOVE)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@ManyToOne(cascade = CascadeType.REMOVE)
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
