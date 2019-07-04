package com.spring.springboot.services;


import com.spring.springboot.dto.CourseDto;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;


public interface CourseService {
	
	public ApiResponse findById(Integer id) throws ObjNotFoundException;
	public ApiResponse findByName(String name) throws ObjNotFoundException;
	public ApiResponse findAll() throws EmptyListException;
	public CourseDto save(CourseDto dto) throws InvalidOperationException;
	public CourseDto update(CourseDto dto) throws InvalidOperationException, ObjNotFoundException;
	public CourseDto delete(CourseDto dto) throws ObjNotFoundException;
	
}
