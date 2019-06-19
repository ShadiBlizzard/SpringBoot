package com.spring.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.services.CourseService;


@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/{name}")
	private String findOne(@PathVariable String name) {
		return courseService.findByName(name);
	}
	
	@GetMapping("/description/{name}")
	private String findDescriptionFromName(@PathVariable String name) {
		return courseService.findDescriptionByName(name);
	}
	
	@GetMapping("/all")
	private List<String> findAll() {
		return courseService.findAll();
	}
	
	
	
	
}
