package com.spring.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
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
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.services.CourseService;


@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/name/{name}")
	public ResponseEntity<ApiResponse> findByName(@PathVariable String name) throws ObjNotFoundException {
		ApiResponse courseDto = courseService.findByName(name);
		return new ResponseEntity<>(courseDto, courseDto.getStatus());
	}
	
	@GetMapping("/description/{id}")
	public ResponseEntity<ApiResponse> findDescriptionFromName(@PathVariable Integer id) throws ObjNotFoundException {
		ApiResponse courseDto = courseService.findById(id);
		return new ResponseEntity<>(courseDto, courseDto.getStatus());
	}
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> findAll() throws EmptyListException {
		ApiResponse listDto =  courseService.findAll();
		return new ResponseEntity<>(listDto, listDto.getStatus());
	}
	
	@PostMapping("/new")
	public ResponseEntity<ApiResponse> saveNewCourse(@RequestBody CourseDto dto) throws InvalidOperationException {
		ApiResponse courseDto = courseService.save(dto);
		return new ResponseEntity<>(courseDto, courseDto.getStatus());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateCourseDescription(@RequestBody CourseDto dto, @PathVariable Integer id) throws InvalidOperationException, ObjNotFoundException {
		dto.setId(id);
		ApiResponse courseDto = courseService.update(dto);
		return new ResponseEntity<>(courseDto, courseDto.getStatus());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) throws ObjNotFoundException {

		ApiResponse courseDto = courseService.delete(id);
		return new ResponseEntity<>(courseDto, courseDto.getStatus());
	}

	
}
