package com.spring.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.services.StudentService;


@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	public findStudentById(int id) {
		
	}

}
