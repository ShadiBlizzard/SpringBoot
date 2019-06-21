package com.spring.springboot.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExamsJsonRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "student", required=false)
	private Integer student;
	
	@JsonProperty(value = "course", required=false)
	private String course;
	
	@JsonProperty(value = "evaluation", required=false)
	private Integer evaluation;

	public Integer getStudent() {
		return student;
	}

	public void setStudent(Integer student) {
		this.student = student;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Integer getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}
	

}
