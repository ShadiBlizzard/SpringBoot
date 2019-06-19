package com.spring.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springboot.entities.Course;
import com.spring.springboot.repository.CoursesRepository;

@Service
public class CourseService {
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	public Course findByName(String name) {
		return coursesRepository.findByName(name);
	}

}
