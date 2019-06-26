package com.spring.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.services.CourseService;


@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/{name}")
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
	
	@PostMapping("/new")
	private String saveNewCourse(@RequestBody Map<String, String> body) {
		String name = body.get("name");
		String description = body.get("description");
		
		return courseService.save(name, description);
	}
	
	@PutMapping("/update/{name}")
	private String updateCourseDescription(@PathVariable String name, @RequestBody Map<String, String> body) {
		return courseService.update(name, body.get("description"));
	}
	
	/**
	 * it deletes a course and all the exams informations related to it (operates with CASCADE)
	 * @param name
	 * @return
	 */
	@DeleteMapping("/delete/{name}")
	private String delete(@PathVariable String name) {
		return courseService.delete(name);
	}
	
	
	
	
}
