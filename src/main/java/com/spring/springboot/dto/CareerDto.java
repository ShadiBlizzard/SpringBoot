package com.spring.springboot.dto;

import java.io.Serializable;

public class CareerDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private CareerIdDto careerId;
	
	public CareerDto() {}

	public CareerDto(CareerIdDto careerId) {
		this.careerId = careerId;
	}

	public CareerIdDto getCareerId() {
		return careerId;
	}

	public void setCareerId(CareerIdDto careerId) {
		this.careerId = careerId;
	}
	
}
