package com.spring.springboot.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.CareerDto;
import com.spring.springboot.entities.Career;
import com.spring.springboot.entities.Course;
import com.spring.springboot.entities.Student;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.repository.CareerRepository;
import com.spring.springboot.repository.CoursesRepository;
import com.spring.springboot.repository.StudentRepository;
import com.spring.springboot.services.CareerService;
import com.spring.springboot.utils.MapperUtils;
import com.spring.springboot.utils.StringUtils;

@Service
@Transactional(readOnly = true)
public class CareerServiceImplementation implements CareerService {
	
	@Autowired
	private CareerRepository careerRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private MapperUtils mapper;
	
	@Override
	public ApiResponse findAll() throws EmptyListException {
		List<Career> careers = careerRepository.findAll();
		if(careers.isEmpty())
			throw new EmptyListException(String.format(StringUtils.EMPTY_LIST, StringUtils.EXAM + "s"));
		return new ApiResponse(HttpStatus.FOUND, mapper.mapAll(careers, CareerDto.class));
	}

	@Override
	public ApiResponse findByStudent(Integer id) throws ObjNotFoundException, EmptyListException {
		Optional<Student> student = studentRepository.findById(id);
		if(!student.isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.STUDENT, "id", id));
		
		List<Career> careers = careerRepository.findCareerByStudent(student.get().getName(), student.get().getSurname());
		if(careers.isEmpty())
			throw new EmptyListException(String.format(StringUtils.EMPTY_LIST, StringUtils.EXAM+ "s"));
		return new ApiResponse(HttpStatus.FOUND, mapper.mapAll(careers, CareerDto.class));
	}

	@Override
	public ApiResponse findByCourse(String name) throws ObjNotFoundException, EmptyListException {
		Course course = coursesRepository.findByName(name);
		if(course == null)
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_NAME,name));
		List<Career> careers = careerRepository.findCareerByCourse(name);
		if(careers.isEmpty())
			throw new EmptyListException(String.format(StringUtils.EMPTY_LIST, StringUtils.EXAM + "s"));
		return new ApiResponse(HttpStatus.FOUND, mapper.mapAll(careers, CareerDto.class));
	}

	@Override
	public ApiResponse findByCourseAndEvaluation(String name, Integer evaluation) throws ObjNotFoundException {
		Course course = coursesRepository.findByName(name);
		if(course == null)
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_NAME, name));
		List<Career> careers = careerRepository.findCareerByCourseAndEvaluation(name, evaluation);
		if(careers.isEmpty())
			throw new IllegalStateException();
		return new ApiResponse(HttpStatus.FOUND, mapper.mapAll(careers, CareerDto.class));
	}

}
