package com.spring.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.entities.Career;
import com.spring.springboot.services.CareerService;

@RestController
@RequestMapping("/career")
@CrossOrigin(origins = "*")
public class CareerController {
	
	@Autowired
	private CareerService careerService;
	
	@GetMapping("/all")
	public List<Career> all(){
		return careerService.findAll();
	}
	
	@GetMapping("/student/{name}/{surname}")
	public List<Career> byStudent(@PathVariable String name, @PathVariable String surname){
		return careerService.findByStudent(name, surname);
	}
	
	@GetMapping("/course/{coursename}")
	public List<Career> byCourse(@PathVariable String coursename){
		return careerService.findByCourse(coursename);
	}
	
	@GetMapping("/course/{coursename}/evaluation/{evl}")
	public List<Career> byCourseAndEvaluation(@PathVariable String coursename, @PathVariable String evl){
		return careerService.findByCourseAndEvaluation(coursename, evl);
	}
	
	
	
	

}
