package com.spring.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.services.CareerService;

@RestController
@RequestMapping("/career")
@CrossOrigin(origins = "*")
public class CareerController {
	
	@Autowired
	private CareerService careerService;
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> all(Pageable pageable) throws EmptyListException{
		ApiResponse careers = careerService.findAll(pageable);
		return new ResponseEntity<>(careers, careers.getStatus());
	}
	
	@GetMapping("/bystudent/{id}")
	public ResponseEntity<ApiResponse> byStudent(@PathVariable Integer id, Pageable pageable) throws ObjNotFoundException, EmptyListException{
		
		ApiResponse careers = careerService.findByStudent(id, pageable);
		return new ResponseEntity<>(careers, careers.getStatus());
	}
	
	@GetMapping("/bycourse/{name}")
	public ResponseEntity<ApiResponse> byCourse(@PathVariable String name, Pageable pageable) throws ObjNotFoundException, EmptyListException{
		ApiResponse careers = careerService.findByCourse(name, pageable);
		return new ResponseEntity<>(careers, careers.getStatus());
	}
	
	@GetMapping("/bycoursewithevaluation")
	public ResponseEntity<ApiResponse> byCourseAndEvaluation(@RequestParam String name, @RequestParam Integer evaluation, Pageable pageable) throws ObjNotFoundException{
		ApiResponse careers = careerService.findByCourseAndEvaluation(name, evaluation, pageable);
		return new ResponseEntity<>(careers, careers.getStatus());
	}

}
