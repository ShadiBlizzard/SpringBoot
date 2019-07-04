package com.spring.springboot.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.StudentDto;
import com.spring.springboot.entities.Student;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.InsertionException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.repository.StudentRepository;
import com.spring.springboot.services.StudentService;
import com.spring.springboot.utils.MapperUtils;
import com.spring.springboot.utils.StringUtils;

@Service
@Transactional(readOnly = false)
public class StudentServiceImplementation implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private MapperUtils mapper;

	@Override
	@Transactional(readOnly = true)
	public ApiResponse findStudentById(int id) throws ObjNotFoundException {
		if(!studentRepository.findById(id).isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.STUDENT, "id", id));
		return new ApiResponse(HttpStatus.FOUND, mapper.map(studentRepository.findById(id).get(), StudentDto.class));
	}

	@Override
	@Transactional(readOnly = true)
	public ApiResponse findAll() throws EmptyListException {
		List<Student> students = studentRepository.findAll();
		if(students.isEmpty())
			throw new EmptyListException(String.format(StringUtils.EMPTY_LIST, StringUtils.STUDENT + "s"));
		return new ApiResponse(HttpStatus.FOUND, mapper.mapAll(students, StudentDto.class));	
	}

	@Override
	@Transactional(readOnly = true)
	public ApiResponse findStudentsByNameAndSurname(StudentDto dto) throws ObjNotFoundException {
		List<Student> students = studentRepository.findByNameAndSurname(dto.getName(), dto.getSurname());
		
		if(students.isEmpty()) 
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_NAME, StringUtils.STUDENT, "name and surname", dto.getName() + " " + dto.getSurname()));
		return new ApiResponse(HttpStatus.FOUND, mapper.mapAll(students, StudentDto.class));
	}

	@Override
	public ApiResponse save(StudentDto dto) throws InvalidOperationException, InsertionException {
		if(dto.getId() != null)
			throw new InvalidOperationException(StringUtils.INVALID_UPDATE_OP);
		Student student = mapper.map(dto, Student.class);
		Student insert = studentRepository.save(student);
		if (insert == null) 
			throw new InsertionException(String.format(StringUtils.INSERTION_ERROR, StringUtils.STUDENT));
		return new ApiResponse(HttpStatus.OK, mapper.map(insert, StudentDto.class));	
	}

	@Override
	public ApiResponse update(StudentDto dto) throws ObjNotFoundException, InvalidOperationException, InsertionException {
		//controllo che ci sia un id da cercare
		if(dto.getId() == null)
			throw new InvalidOperationException(StringUtils.INVALID_INSERT_OP);
		//controllo che questo id sia effettivamente presente nel db
		if(!studentRepository.findById(dto.getId()).isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.STUDENT, "id", dto.getId()));
		Student student = mapper.map(dto, Student.class);
		if(studentRepository.save(student) == null)
			throw new InsertionException(String.format(StringUtils.INSERTION_ERROR, StringUtils.STUDENT));
		return new ApiResponse(HttpStatus.OK, String.format(StringUtils.UPDATE_SUCCESS, student.getClass().getName()));	
	}

	@Override
	public ApiResponse delete(StudentDto dto) throws ObjNotFoundException {
		if(!studentRepository.findById(dto.getId()).isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.STUDENT, "id", dto.getId()));
		Student student = mapper.map(dto, Student.class);
		studentRepository.delete(student);
		return new ApiResponse(HttpStatus.OK, String.format(StringUtils.DELETE_SUCCESS, StringUtils.DELETE_SUCCESS));
	}

}
