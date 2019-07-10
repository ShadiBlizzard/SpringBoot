package com.spring.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.dto.StudentDto;
import com.spring.springboot.exceptions.ApiResponse;
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
	public ResponseEntity<ApiResponse> findStudentById(@PathVariable("id") int id) throws ObjNotFoundException {
		ApiResponse dto = studentService.findStudentById(id);
		return new ResponseEntity<>(dto, dto.getStatus());
	}
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> findAll(Pageable pageable) throws EmptyListException {
		ApiResponse listDto= studentService.findAll(pageable);
		return new ResponseEntity<>(listDto, listDto.getStatus());
	}
	
	@GetMapping("/byname")
	public ResponseEntity<ApiResponse> findStudentByNameAndSurname(@RequestParam String name, @RequestParam String surname, Pageable pageable) throws ObjNotFoundException {
		ApiResponse responseDto = studentService.findStudentsByNameAndSurname(new StudentDto(name, surname), pageable);
		return new ResponseEntity<>(responseDto, responseDto.getStatus());
	}
	
	@PostMapping("/new")
	public ResponseEntity<ApiResponse> insertNewStudent(@RequestBody StudentDto dto) throws InvalidOperationException {
		ApiResponse responseDto = studentService.save(dto);
		return new ResponseEntity<>(responseDto, responseDto.getStatus());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateStudent(@RequestBody StudentDto dto, @PathVariable Integer id) throws ObjNotFoundException, InvalidOperationException {
		dto.setId(id);
		ApiResponse responseDto = studentService.update(dto);
		return new ResponseEntity<>(responseDto, responseDto.getStatus());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Integer id) throws ObjNotFoundException {
		ApiResponse responseDto = studentService.delete(id);
		return new ResponseEntity<>(responseDto, responseDto.getStatus());
	}

}
