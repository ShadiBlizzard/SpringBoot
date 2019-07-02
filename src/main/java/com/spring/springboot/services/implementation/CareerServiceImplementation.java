package com.spring.springboot.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.CareerDto;
import com.spring.springboot.entities.Career;
import com.spring.springboot.repository.CareerRepository;
import com.spring.springboot.services.CareerService;
import com.spring.springboot.utils.MapperUtils;

@Service
@Transactional(readOnly = true)
public class CareerServiceImplementation implements CareerService {
	
	@Autowired
	private CareerRepository careerRepository;
	
	@Autowired
	private MapperUtils mapper;
	
	@Override
	public List<CareerDto> findAll() {
		List<Career> careers = careerRepository.findAll();
		if(careers.size() == 0)
			throw new IllegalStateException();
		return mapper.mapAll(careers, CareerDto.class);
	}

	@Override
	public List<CareerDto> findByStudent(CareerDto dto) {
		List<Career> careers = careerRepository.findCareerByStudent(dto.getCareerId().getStudentname(), dto.getCareerId().getStudentsurname());
		if(careers.size() == 0)
			throw new IllegalStateException();
		return mapper.mapAll(careers, CareerDto.class);
	}

	@Override
	public List<CareerDto> findByCourse(CareerDto dto) {
		List<Career> careers = careerRepository.findCareerByCourse(dto.getCareerId().getCoursename());
		if(careers.size() == 0)
			throw new IllegalStateException();
		return mapper.mapAll(careers, CareerDto.class);
	}

	@Override
	public List<CareerDto> findByCourseAndEvaluation(CareerDto dto) {
		List<Career> careers = careerRepository.findCareerByCourseAndEvaluation(dto.getCareerId().getCoursename(), dto.getCareerId().getEvaluation());
		if(careers.size() == 0)
			throw new IllegalStateException();
		return mapper.mapAll(careers, CareerDto.class);
	}

}
