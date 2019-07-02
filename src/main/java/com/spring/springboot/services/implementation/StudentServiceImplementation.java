package com.spring.springboot.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.StudentDto;
import com.spring.springboot.entities.Student;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;
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
	public StudentDto findStudentById(int id) throws ObjNotFoundException {
		//Student student = studentRepository.findById(id).get();
		if(!studentRepository.findById(id).isPresent())
			throw new ObjNotFoundException("Student of id " + id + " has not been found");
		return mapper.map(studentRepository.findById(id).get(), StudentDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudentDto> findAll() throws EmptyListException {
		List<Student> students = studentRepository.findAll();
		if(students.isEmpty())
			throw new EmptyListException("No students were found");
		return mapper.mapAll(students, StudentDto.class);	
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudentDto> findStudentsByNameAndSurname(StudentDto dto) throws ObjNotFoundException {
		List<Student> students = studentRepository.findByNameAndSurname(dto.getName(), dto.getSurname());
		
		if(students.isEmpty()) 
			throw new ObjNotFoundException("No student named " + dto.getName() + " " + dto.getSurname() + " was found.");
		return mapper.mapAll(students, StudentDto.class);
	}

	@Override
	public StudentDto save(StudentDto dto) throws InvalidOperationException {
		if(dto.getId() != null)
			throw new InvalidOperationException("Errore! Stai tentando di fare un update ma devi fare un insert!");
		Student student = mapper.map(dto, Student.class);
		Student insert = studentRepository.save(student);
		return mapper.map(insert, StudentDto.class);	
	}

	@Override
	public StudentDto update(StudentDto dto) throws ObjNotFoundException, InvalidOperationException {
		//controllo che ci sia un id da cercare
		if(dto.getId() == null)
			throw new InvalidOperationException("Errore! Stai tentando di fare un insert ma devi fare un update!");
		//controllo che questo id sia effettivamente presente nel db
		if(!studentRepository.findById(dto.getId()).isPresent())
			throw new ObjNotFoundException("Student with id " + dto.getId() + " has not been found");
		Student student = mapper.map(dto, Student.class);
		Student update = studentRepository.save(student);
		return mapper.map(update, StudentDto.class);	
	}

	@Override
	public StudentDto delete(StudentDto dto) throws ObjNotFoundException {
		if(!studentRepository.findById(dto.getId()).isPresent())
			throw new ObjNotFoundException("Student with id " + dto.getId() + " has not been found");
		Student student = mapper.map(dto, Student.class);
		studentRepository.delete(student);
		return dto;
	}

}
