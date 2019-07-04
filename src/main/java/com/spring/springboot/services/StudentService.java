package com.spring.springboot.services;

import com.spring.springboot.dto.StudentDto;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.InsertionException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;

public interface StudentService {
	
	public ApiResponse findStudentById(int id) throws ObjNotFoundException;
	public ApiResponse findAll() throws EmptyListException;
	public ApiResponse findStudentsByNameAndSurname(StudentDto dto) throws ObjNotFoundException;
	public ApiResponse save(StudentDto dto) throws InvalidOperationException, InsertionException;
	public ApiResponse update(StudentDto dto) throws ObjNotFoundException, InvalidOperationException, InsertionException;
	public ApiResponse delete(StudentDto dto) throws ObjNotFoundException;

}
