package com.spring.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.entities.Exams;
import com.spring.springboot.services.ExamsService;

@RestController
@RequestMapping("/exams")
public class ExamsController {
	
	@Autowired
	private ExamsService examsService;
	
	@PostMapping("/save/{student}/{course}/{evaluation}")
	public Exams save(@PathVariable Integer student, @PathVariable String course, @PathVariable Integer evaluation) {
		try {
			return  examsService.save(student, course, evaluation);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping("/update/{student}/{course}/{evaluation}")
	public Exams update(@PathVariable Integer student, @PathVariable String course, @PathVariable Integer evaluation) {
		try {
			return  examsService.update(student, course, evaluation);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/delete/{student}/{course}")
	public String delete(@PathVariable Integer student, @PathVariable String course) {
		try {
			examsService.delete(student, course);
			return "OK";
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
}
