package com.spring.springboot.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.CareerDto;
import com.spring.springboot.entities.Career;
import com.spring.springboot.repository.CareerRepository;
import com.spring.springboot.services.CareerService;

@Service
@Transactional(readOnly = true)
public class CareerServiceImplementation implements CareerService {
	
	@Autowired
	private CareerRepository careerRepository;

	@Autowired
	private ModelMapper modelMapper;
	
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
	
	public CareerDto convertCareerToDto(Career career) {
		return modelMapper.map(career, CareerDto.class);
	}
	
	public Career convertDtoToCareer(CareerDto dto) {
		return modelMapper.map(dto, Career.class);
	}
	

}
