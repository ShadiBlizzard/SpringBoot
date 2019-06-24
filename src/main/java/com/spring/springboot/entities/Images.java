package com.spring.springboot.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "images")
public class Images {
	
	@Id
	@NotNull
	private Integer id;
	
	private byte[] value;

	public Images() {}
	
	public Images(@NotNull Integer id, byte[] value) {
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
