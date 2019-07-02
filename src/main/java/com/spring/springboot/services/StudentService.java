package com.spring.springboot.services;

import java.util.List;

import com.spring.springboot.dto.StudentDto;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;

public interface StudentService {
	
	public StudentDto findStudentById(int id) throws ObjNotFoundException;
	public List<StudentDto> findAll() throws EmptyListException;
	public List<StudentDto> findStudentsByNameAndSurname(StudentDto dto) throws ObjNotFoundException;
	public StudentDto save(StudentDto dto) throws InvalidOperationException;
	public StudentDto update(StudentDto dto) throws ObjNotFoundException, InvalidOperationException;
	public StudentDto delete(StudentDto dto) throws ObjNotFoundException;

}
