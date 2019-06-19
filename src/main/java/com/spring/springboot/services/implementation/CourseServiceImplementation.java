package com.spring.springboot.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springboot.entities.Course;
import com.spring.springboot.repository.CoursesRepository;
import com.spring.springboot.services.CourseService;

@Service
public class CourseServiceImplementation implements CourseService {
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Override
	public Course findByName(String name) {
		return coursesRepository.findByName(name);
	}

}
