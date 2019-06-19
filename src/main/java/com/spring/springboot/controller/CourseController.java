package com.spring.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.repository.CoursesRepository;

@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	private CoursesRepository coursesRepository;
	
	@GetMapping("/count")
	private String getCount() {
		return coursesRepository.findAll().get(0).getName();
	}
}
