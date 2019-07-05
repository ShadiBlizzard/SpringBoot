package com.spring.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<ApiResponse> all() throws EmptyListException{
		ApiResponse careers = careerService.findAll();
		return new ResponseEntity<>(careers, careers.getStatus());
	}
	
	@GetMapping("/bystudent/{id}")
	public ResponseEntity<ApiResponse> byStudent(@PathVariable Integer id) throws ObjNotFoundException, EmptyListException{
		
		ApiResponse careers = careerService.findByStudent(id);
		return new ResponseEntity<>(careers, careers.getStatus());
	}
	
	@GetMapping("/bycourse/{name}")
	public ResponseEntity<ApiResponse> byCourse(@PathVariable String name) throws ObjNotFoundException, EmptyListException{
		ApiResponse careers = careerService.findByCourse(name);
		return new ResponseEntity<>(careers, careers.getStatus());
	}
	
	@GetMapping("/bycoursewithevaluation")
	public ResponseEntity<ApiResponse> byCourseAndEvaluation(@RequestParam String name, @RequestParam Integer evaluation) throws ObjNotFoundException{
		ApiResponse careers = careerService.findByCourseAndEvaluation(name, evaluation);
		return new ResponseEntity<>(careers, careers.getStatus());
	}

}
