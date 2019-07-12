package com.spring.springboot.services;

import org.springframework.data.domain.Pageable;

import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.ObjNotFoundException;

public interface CareerService {
	
	public ApiResponse findAll(Pageable pageable) throws EmptyListException;
	public ApiResponse findByStudent(Integer id, Pageable pageable) throws ObjNotFoundException, EmptyListException;
	public ApiResponse findByCourse(String name, Pageable pageable) throws ObjNotFoundException, EmptyListException;
	public ApiResponse findByCourseAndEvaluation(String name, Integer evaluation, Pageable pageable) throws ObjNotFoundException;
	public ApiResponse count();
	
}
