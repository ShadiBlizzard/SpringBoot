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

import com.spring.springboot.dto.StudentDto;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.services.StudentService;


@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {
	
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> findStudentById(@PathVariable("id") int id) throws ObjNotFoundException {
		StudentDto dto = studentService.findStudentById(id);
		return new ResponseEntity<>(dto, HttpStatus.FOUND);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<StudentDto>> findAll() throws EmptyListException {
		List<StudentDto> listDto= studentService.findAll();
		return new ResponseEntity<>(listDto, HttpStatus.FOUND);
	}
	
	@PostMapping("/byname")
	public ResponseEntity<List<StudentDto>> findStudentByNameAndSurname(@RequestBody StudentDto dto) throws ObjNotFoundException {
		List<StudentDto> responseDto = studentService.findStudentsByNameAndSurname(dto);
		return new ResponseEntity<>(responseDto, HttpStatus.FOUND);
	}
	
	@PostMapping("/new")
	public ResponseEntity<StudentDto> insertNewStudent(@RequestBody StudentDto dto) throws InvalidOperationException {
		StudentDto responseDto = studentService.save(dto);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto dto) throws ObjNotFoundException, InvalidOperationException {
		StudentDto responseDto = studentService.update(dto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<StudentDto> deleteStudent(@RequestBody StudentDto dto) throws ObjNotFoundException {
		StudentDto responseDto = studentService.delete(dto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
