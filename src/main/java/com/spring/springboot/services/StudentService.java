package com.spring.springboot.services;

import org.springframework.data.domain.Pageable;

import com.spring.springboot.dto.StudentDto;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;

public interface StudentService {
	
	public ApiResponse findStudentById(int id) throws ObjNotFoundException;
	public ApiResponse findAll(Pageable pageble) throws EmptyListException;
	public ApiResponse findStudentsByNameAndSurname(StudentDto dto, Pageable pageable) throws ObjNotFoundException;
	public ApiResponse save(StudentDto dto) throws InvalidOperationException;
	public ApiResponse update(StudentDto dto) throws ObjNotFoundException, InvalidOperationException;
	public ApiResponse delete(Integer dto) throws ObjNotFoundException;

}
