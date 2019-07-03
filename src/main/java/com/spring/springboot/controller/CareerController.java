package com.spring.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.dto.CareerDto;
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
	public ResponseEntity<List<CareerDto>> all() throws EmptyListException{
		List<CareerDto> careers = careerService.findAll();
		return new ResponseEntity<>(careers, HttpStatus.FOUND);
	}
	
	@PostMapping("/bystudent")
	public ResponseEntity<List<CareerDto>> byStudent(@RequestBody CareerDto dto) throws ObjNotFoundException, EmptyListException{
		List<CareerDto> careers = careerService.findByStudent(dto);
		return new ResponseEntity<>(careers, HttpStatus.FOUND);
	}
	
	@PostMapping("/bycourse")
	public ResponseEntity<List<CareerDto>> byCourse(@RequestBody CareerDto dto) throws ObjNotFoundException, EmptyListException{
		List<CareerDto> careers = careerService.findByCourse(dto);
		return new ResponseEntity<>(careers, HttpStatus.FOUND);
	}
	
	@PostMapping("/bycoursewithevaluation")
	public ResponseEntity<List<CareerDto>> byCourseAndEvaluation(@RequestBody CareerDto dto) throws ObjNotFoundException{
		List<CareerDto> careers = careerService.findByCourseAndEvaluation(dto);
		return new ResponseEntity<>(careers, HttpStatus.FOUND);
	}

}
