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
	public StudentDto findStudentById(int id) throws ObjNotFoundException {
		if(!studentRepository.findById(id).isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.STUDENT, "id", id));
		return mapper.map(studentRepository.findById(id).get(), StudentDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudentDto> findAll() throws EmptyListException {
		List<Student> students = studentRepository.findAll();
		if(students.isEmpty())
			throw new EmptyListException(String.format(StringUtils.EMPTY_LIST, StringUtils.STUDENT + "s"));
		return mapper.mapAll(students, StudentDto.class);	
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudentDto> findStudentsByNameAndSurname(StudentDto dto) throws ObjNotFoundException {
		List<Student> students = studentRepository.findByNameAndSurname(dto.getName(), dto.getSurname());
		
		if(students.isEmpty()) 
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_NAME, StringUtils.STUDENT, "name and surname", dto.getName() + " " + dto.getSurname()));
		return mapper.mapAll(students, StudentDto.class);
	}

	@Override
	public StudentDto save(StudentDto dto) throws InvalidOperationException {
		if(dto.getId() != null)
			throw new InvalidOperationException(StringUtils.INVALID_UPDATE_OP);
		Student student = mapper.map(dto, Student.class);
		Student insert = studentRepository.save(student);
		return mapper.map(insert, StudentDto.class);	
	}

	@Override
	public StudentDto update(StudentDto dto) throws ObjNotFoundException, InvalidOperationException {
		//controllo che ci sia un id da cercare
		if(dto.getId() == null)
			throw new InvalidOperationException(StringUtils.INVALID_INSERT_OP);
		//controllo che questo id sia effettivamente presente nel db
		if(!studentRepository.findById(dto.getId()).isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.STUDENT, "id", dto.getId()));
		Student student = mapper.map(dto, Student.class);
		Student update = studentRepository.save(student);
		return mapper.map(update, StudentDto.class);	
	}

	@Override
	public StudentDto delete(StudentDto dto) throws ObjNotFoundException {
		if(!studentRepository.findById(dto.getId()).isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.STUDENT, "id", dto.getId()));
		Student student = mapper.map(dto, Student.class);
		studentRepository.delete(student);
		return dto;
	}

}
