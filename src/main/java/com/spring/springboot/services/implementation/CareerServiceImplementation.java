package com.spring.springboot.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springboot.entities.Career;
import com.spring.springboot.repository.CareerRepository;
import com.spring.springboot.services.CareerService;

@Service
public class CareerServiceImplementation implements CareerService {
	
	@Autowired
	private CareerRepository careerRepository;

	@Override
	public List<Career> findAll() {
		return careerRepository.findAll();
	}

	@Override
	public List<Career> findByStudent(String name, String surname) {
		return careerRepository.findCareerByStudent(name, surname);
	}

	@Override
	public List<Career> findByCourse(String coursename) {
		return careerRepository.findCareerByCourse(coursename);
	}

	@Override
	public List<Career> findByCourseAndEvaluation(String course, String evaluation) {
		return careerRepository.findCareerByCourseAndEvaluation(course, evaluation);
	}
	
	
	

}
