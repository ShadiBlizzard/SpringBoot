package com.spring.springboot.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CareerId implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String studentname;
	private String studentsurname;
	private String coursename;
	private String evaluation;

	public CareerId() {}

	public CareerId(String studentname, String studentsurname, String coursename, String evaluation) {
		super();
		this.studentname = studentname;
		this.studentsurname = studentsurname;
		this.coursename = coursename;
		this.evaluation = evaluation;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getStudentsurname() {
		return studentsurname;
	}

	public void setStudentsurname(String studentsurname) {
		this.studentsurname = studentsurname;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}


}
