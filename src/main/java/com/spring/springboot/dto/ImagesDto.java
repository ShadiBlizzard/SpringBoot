package com.spring.springboot.dto;

import java.io.Serializable;

public class ImagesDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private byte[] value;

	public ImagesDto() {}

	public ImagesDto(Integer id, byte[] value) {
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

}
