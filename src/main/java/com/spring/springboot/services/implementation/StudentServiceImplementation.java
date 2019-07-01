package com.spring.springboot.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.StudentDto;
import com.spring.springboot.entities.Student;
import com.spring.springboot.repository.StudentRepository;
import com.spring.springboot.services.StudentService;
import com.spring.springboot.utils.MapperUtils;

@Service
@Transactional(readOnly = false)
public class StudentServiceImplementation implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private MapperUtils mapper;

	@Override
	@Transactional(readOnly = true)
	public StudentDto findStudentById(int id) {
		Student student = studentRepository.findById(id).get();
		return mapper.map(student, StudentDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudentDto> findAll() {
		List<Student> students = studentRepository.findAll();
		return mapper.mapAll(students, StudentDto.class);	
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudentDto> findStudentsByNameAndSurname(StudentDto dto) {
		List<Student> students = studentRepository.findByNameAndSurname(dto.getName(), dto.getSurname());
		return mapper.mapAll(students, StudentDto.class);
	}

	@Override
	public StudentDto save(StudentDto dto) throws IllegalStateException {
		if(dto.getId() != null)
			throw new IllegalStateException();
		Student student = mapper.map(dto, Student.class);
		Student insert = studentRepository.save(student);
		return mapper.map(insert, StudentDto.class);	
	}

	@Override
	public StudentDto update(StudentDto dto) {
		//controllo che ci sia un id da cercare
		if(dto.getId() == null)
			throw new IllegalStateException();
		//controllo che questo id sia effettivamente presente nel db
		if(findStudentById(dto.getId()).getId() == null)
			throw new IllegalStateException();
		Student student = mapper.map(dto, Student.class);
		Student update = studentRepository.save(student);
		return mapper.map(update, StudentDto.class);	
	}

	@Override
	public StudentDto delete(StudentDto dto) {
		if(findStudentById(dto.getId()).getId() == null)
			throw new IllegalStateException();
		Student student = mapper.map(dto, Student.class);
		studentRepository.delete(student);
		return dto;
	}

}
