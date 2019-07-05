package com.spring.springboot.services;

import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.ObjNotFoundException;

public interface CareerService {
	
	public ApiResponse findAll() throws EmptyListException;
	public ApiResponse findByStudent(Integer id) throws ObjNotFoundException, EmptyListException;
	public ApiResponse findByCourse(String name) throws ObjNotFoundException, EmptyListException;
	public ApiResponse findByCourseAndEvaluation(String name, Integer evaluation) throws ObjNotFoundException;
	
}
