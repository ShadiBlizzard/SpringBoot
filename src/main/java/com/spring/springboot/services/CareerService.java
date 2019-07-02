package com.spring.springboot.services;

import java.util.List;

import com.spring.springboot.dto.CareerDto;

public interface CareerService {
	
	public List<CareerDto> findAll();
	public List<CareerDto> findByStudent(CareerDto dto);
	public List<CareerDto> findByCourse(CareerDto dto);
	public List<CareerDto> findByCourseAndEvaluation(CareerDto dto);
	
}
