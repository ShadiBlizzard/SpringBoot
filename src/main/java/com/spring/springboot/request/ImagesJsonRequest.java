package com.spring.springboot.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImagesJsonRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "student", required = false)
	private Integer student;
	
	@JsonProperty(value = "image", required = false)
	private byte[] image;

	public Integer getStudent() {
		return student;
	}

	public void setStudent(Integer student) {
		this.student = student;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
