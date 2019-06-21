package com.spring.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.entities.Exams;
import com.spring.springboot.request.ExamsJsonRequest;
import com.spring.springboot.services.ExamsService;

@RestController
@RequestMapping("/exams")
public class ExamsController {
	
	@Autowired
	private ExamsService examsService;
	
	@PostMapping("/save/{student}/{course}/{evaluation}")
	public Exams save(@RequestBody ExamsJsonRequest request) {
		try {
			return  examsService.save(request.getStudent(), request.getCourse(), request.getEvaluation());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping("/update/{student}/{course}/{evaluation}")
	public Exams update(@RequestBody ExamsJsonRequest request) {
		try {
			return  examsService.update(request.getStudent(), request.getCourse(), request.getEvaluation());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestBody ExamsJsonRequest request) {
		try {
			examsService.delete(request.getStudent(), request.getCourse());
			return "OK";
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
}
