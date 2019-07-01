package com.spring.springboot.services;

import java.util.List;

import com.spring.springboot.dto.CourseDto;


public interface CourseService {
	
	public CourseDto findById(Integer id);
	public CourseDto findByName(String name);
	public List<CourseDto> findAll();
	public CourseDto save(CourseDto dto);
	public CourseDto update(CourseDto dto);
	public CourseDto delete(CourseDto dto);
	
}
