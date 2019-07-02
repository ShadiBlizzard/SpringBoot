package com.spring.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.dto.CourseDto;
import com.spring.springboot.services.CourseService;


@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/name/{name}")
	private ResponseEntity<CourseDto> findByName(@PathVariable String name) {
		CourseDto courseDto = courseService.findByName(name);
		return new ResponseEntity<>(courseDto, HttpStatus.FOUND);
	}
	
	@GetMapping("/id/{id}")
	private ResponseEntity<CourseDto> findDescriptionFromName(@PathVariable Integer id) {
		CourseDto courseDto = courseService.findById(id);
		return new ResponseEntity<>(courseDto, HttpStatus.FOUND);
	}
	
	@GetMapping("/all")
	private ResponseEntity<List<CourseDto>> findAll() {
		List<CourseDto> listDto =  courseService.findAll();
		return new ResponseEntity<>(listDto, HttpStatus.FOUND);
	}
	
	@PostMapping("/new")
	private ResponseEntity<CourseDto> saveNewCourse(@RequestBody CourseDto dto) {
		CourseDto courseDto = courseService.save(dto);
		return new ResponseEntity<>(courseDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	private ResponseEntity<CourseDto> updateCourseDescription(@RequestBody CourseDto dto) {
		CourseDto courseDto = courseService.update(dto);
		return new ResponseEntity<>(courseDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	private ResponseEntity<CourseDto> delete(@RequestBody CourseDto dto) {
		CourseDto courseDto = courseService.delete(dto);
		return new ResponseEntity<>(courseDto, HttpStatus.OK);
	}

	
}
