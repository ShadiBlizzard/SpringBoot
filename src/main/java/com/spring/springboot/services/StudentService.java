package com.spring.springboot.services;

import java.util.List;

import com.spring.springboot.dto.StudentDto;

public interface StudentService {
	
	public StudentDto findStudentById(int id);
	public List<StudentDto> findAll();
	public List<StudentDto> findStudentsByNameAndSurname(StudentDto dto);
	public StudentDto save(StudentDto dto) throws IllegalStateException;
	public StudentDto update(StudentDto dto) throws IllegalStateException;
	public StudentDto delete(StudentDto dto);

}
