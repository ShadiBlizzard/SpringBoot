package com.spring.springboot.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="takes")
public class Takes {
	
	@ManyToOne
	@JoinColumn(name ="idstudent")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="idcourse")
	private Course course;
	
	
	
	

}
