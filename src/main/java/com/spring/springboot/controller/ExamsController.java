package com.spring.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.dto.ExamsDto;
import com.spring.springboot.exceptions.ExamAlreadyRegisteredException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.exceptions.UnexistingExamException;
import com.spring.springboot.services.ExamsService;

@RestController
@RequestMapping("/exams")
@CrossOrigin(origins  = "*")
public class ExamsController {
		
	@Autowired
	private ExamsService examsService;
	
	@PostMapping("/save")
	public ResponseEntity<ExamsDto> save(@RequestBody ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException, UnexistingExamException {
		ExamsDto exam = examsService.save(dto);
		return new ResponseEntity<>(exam, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ExamsDto> update(@RequestBody ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException, UnexistingExamException {
		ExamsDto exam = examsService.update(dto);
		return new ResponseEntity<>(exam, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ExamsDto> delete(@RequestBody ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException, UnexistingExamException {
		examsService.delete(dto);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
}
