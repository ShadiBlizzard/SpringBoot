package com.spring.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins  = "*")
public class ExamsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExamsController.class);
	
	@Autowired
	private ExamsService examsService;
	
	@PostMapping("/save")
	public Exams save(@RequestBody ExamsJsonRequest request) {
		try {
			return  examsService.save(request.getStudent(), request.getCourse(), request.getEvaluation());
		} catch (Exception e) {
			logger.error("Errore sulla insert: ", e);
			return null;
		}
	}
	
	@PutMapping("/update")
	public Exams update(@RequestBody ExamsJsonRequest request) {
		try {
			return  examsService.update(request.getStudent(), request.getCourse(), request.getEvaluation());
		} catch (Exception e) {
			logger.error("Errore sull'update: ", e);
			return null;
		}
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestBody ExamsJsonRequest request) {
		try {
			examsService.delete(request.getStudent(), request.getCourse());
			return "OK";
		} catch (Exception e) {
			logger.error("Errore sulla delete: ", e);
			return null;
		}
	}
}
