package com.spring.springboot.services.implementation;

import com.spring.springboot.entities.Course;

public interface CourseService {
	
	public Course findByName(String name);
	
}
