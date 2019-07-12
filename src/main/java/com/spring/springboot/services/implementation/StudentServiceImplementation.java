package com.spring.springboot.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.StudentDto;
import com.spring.springboot.entities.Student;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
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
		Optional<Student> student = studentRepository.findById(id);
		if(student.isPresent())
			return new ApiResponse(HttpStatus.FOUND, mapper.map(student.get(), StudentDto.class));
		else
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.STUDENT, "id", id));
	}

	@Override
	@Transactional(readOnly = true)
	public ApiResponse findAll(Pageable pageable) throws EmptyListException {
		List<Student> students = studentRepository.findAll(pageable).getContent();
		if(students.isEmpty())
			throw new EmptyListException(String.format(StringUtils.EMPTY_LIST, StringUtils.STUDENT + "s"));
		return new ApiResponse(HttpStatus.FOUND, mapper.mapAll(students, StudentDto.class));	
	}

	@Override
	@Transactional(readOnly = true)
	public ApiResponse findStudentsByNameAndSurname(StudentDto dto, Pageable pageable) throws ObjNotFoundException {
		List<Student> students = studentRepository.findByNameAndSurname(dto.getName(), dto.getSurname(), pageable);
		
		if(students.isEmpty()) 
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_NAME, StringUtils.STUDENT, "name and surname", dto.getName() + " " + dto.getSurname()));
		return new ApiResponse(HttpStatus.FOUND, mapper.mapAll(students, StudentDto.class));
	}

	@Override
	public ApiResponse save(StudentDto dto) throws InvalidOperationException {
		if(dto.getId() != null)
			throw new InvalidOperationException(StringUtils.INVALID_UPDATE_OP);
		Student student = mapper.map(dto, Student.class);
		Student insert = studentRepository.save(student);
		return new ApiResponse(HttpStatus.OK, mapper.map(insert, StudentDto.class));	
	}

	@Override
	public ApiResponse update(StudentDto dto) throws ObjNotFoundException, InvalidOperationException {
		//controllo che ci sia un id da cercare
		if(dto.getId() == null)
			throw new InvalidOperationException(StringUtils.INVALID_INSERT_OP);
		//controllo che questo id sia effettivamente presente nel db
		if(!studentRepository.findById(dto.getId()).isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.STUDENT, "id", dto.getId()));
		Student student = mapper.map(dto, Student.class);
		studentRepository.save(student);
		return new ApiResponse(HttpStatus.OK, String.format(StringUtils.UPDATE_SUCCESS, student.getClass().getSimpleName()));	
	}

	@Override
	public ApiResponse delete(Integer id) throws ObjNotFoundException {
		Optional<Student> studentDto= studentRepository.findById(id);
		if(!studentDto.isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.STUDENT, "id",id));
		Student student = mapper.map(studentDto.get(), Student.class);
		studentRepository.delete(student);
		return new ApiResponse(HttpStatus.OK, String.format(StringUtils.DELETE_SUCCESS, student.getClass().getSimpleName()));
	}

	@Override
	public ApiResponse count() {
		Long count = studentRepository.count();
		return new ApiResponse(HttpStatus.OK, count);
	}
	
	
}
