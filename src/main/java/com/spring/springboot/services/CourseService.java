package com.spring.springboot.services;


import org.springframework.data.domain.Pageable;

import com.spring.springboot.dto.CourseDto;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;


public interface CourseService {
	
	public ApiResponse findById(Integer id) throws ObjNotFoundException;
	public ApiResponse findByName(String name) throws ObjNotFoundException;
	public ApiResponse findAll(Pageable pageable) throws EmptyListException;
	public ApiResponse save(CourseDto dto) throws InvalidOperationException;
	public ApiResponse update(CourseDto dto) throws InvalidOperationException, ObjNotFoundException;
	public ApiResponse delete(Integer dto) throws ObjNotFoundException;
	public ApiResponse count();
}
