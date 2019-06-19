package com.spring.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.entities.Course;
import com.spring.springboot.services.CourseService;


@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses/{name}")
	private Course findOne(@PathVariable String name) {
		return courseService.findByName(name);
	}
}
