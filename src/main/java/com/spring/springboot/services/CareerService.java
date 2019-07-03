package com.spring.springboot.services;

import java.util.List;

import com.spring.springboot.dto.CareerDto;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.ObjNotFoundException;

public interface CareerService {
	
	public List<CareerDto> findAll() throws EmptyListException;
	public List<CareerDto> findByStudent(CareerDto dto) throws ObjNotFoundException, EmptyListException;
	public List<CareerDto> findByCourse(CareerDto dto) throws ObjNotFoundException, EmptyListException;
	public List<CareerDto> findByCourseAndEvaluation(CareerDto dto) throws ObjNotFoundException;
	
}
