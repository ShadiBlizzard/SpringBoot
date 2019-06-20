package com.spring.springboot.services;

import java.util.List;

import com.spring.springboot.entities.Career;

public interface CareerService {
	
	public List<Career> findAll();
	public List<Career> findByStudent(String name, String surname);
	public List<Career> findByCourse(String coursename);
	public List<Career> findByCourseAndEvaluation(String evaluation);
	
}
