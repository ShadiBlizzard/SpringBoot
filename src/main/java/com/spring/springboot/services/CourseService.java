package com.spring.springboot.services;

import com.spring.springboot.entities.Course;

public interface CourseService {
	
	public Course findByName(String name);
	
}
