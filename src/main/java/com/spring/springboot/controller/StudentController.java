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

import com.spring.springboot.services.StudentService;


@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/{id}")
	public String findStudentById(@PathVariable int id) {
		return studentService.findStudentById(id);
	}
	
	@GetMapping("/all")
	public List<String> findAll() {
		return studentService.findAll();
	}
	
	@PostMapping("/new")
	public String insertNewStudent(@RequestBody Map<String, String> body) {
		System.out.println(studentService.save(body.get("name"), body.get("surname")));
		return studentService.save(body.get("name"), body.get("surname"));
	}
	
	@PutMapping("/update/{id}")
	public String updateStudent(@PathVariable int id, @RequestBody Map<String, String> body) {
		return studentService.update(id, body.get("name"), body.get("surname"));
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable int id) {
		return studentService.delete(id);
	}

}
