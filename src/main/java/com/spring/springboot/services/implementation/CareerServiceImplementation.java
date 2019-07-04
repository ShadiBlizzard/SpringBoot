package com.spring.springboot.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.CareerDto;
import com.spring.springboot.entities.Career;
import com.spring.springboot.entities.Course;
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
	public List<CareerDto> findAll() throws EmptyListException {
		List<Career> careers = careerRepository.findAll();
		if(careers.isEmpty())
			throw new EmptyListException(String.format(StringUtils.EMPTY_LIST, StringUtils.EXAM + "s"));
		return mapper.mapAll(careers, CareerDto.class);
	}

	@Override
	public List<CareerDto> findByStudent(CareerDto dto) throws ObjNotFoundException, EmptyListException {
		if(studentRepository.findByNameAndSurname(dto.getCareerId().getStudentname(), dto.getCareerId().getStudentsurname()).isEmpty())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_NAME, StringUtils.STUDENT, dto.getCareerId().getStudentname() + dto.getCareerId().getStudentsurname()));
		
		List<Career> careers = careerRepository.findCareerByStudent(dto.getCareerId().getStudentname(), dto.getCareerId().getStudentsurname());
		if(careers.isEmpty())
			throw new EmptyListException(String.format(StringUtils.EMPTY_LIST, StringUtils.EXAM+ "s"));
		return mapper.mapAll(careers, CareerDto.class);
	}

	@Override
	public List<CareerDto> findByCourse(CareerDto dto) throws ObjNotFoundException, EmptyListException {
		Course course = coursesRepository.findByName(dto.getCareerId().getCoursename());
		if(course == null)
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_NAME, dto.getCareerId().getCoursename()));
		List<Career> careers = careerRepository.findCareerByCourse(dto.getCareerId().getCoursename());
		if(careers.isEmpty())
			throw new EmptyListException(String.format(StringUtils.EMPTY_LIST, StringUtils.EXAM + "s"));
		return mapper.mapAll(careers, CareerDto.class);
	}

	@Override
	public List<CareerDto> findByCourseAndEvaluation(CareerDto dto) throws ObjNotFoundException {
		Course course = coursesRepository.findByName(dto.getCareerId().getCoursename());
		if(course == null)
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_NAME, dto.getCareerId().getCoursename()));
		List<Career> careers = careerRepository.findCareerByCourseAndEvaluation(dto.getCareerId().getCoursename(), dto.getCareerId().getEvaluation());
		if(careers.isEmpty())
			throw new IllegalStateException();
		return mapper.mapAll(careers, CareerDto.class);
	}

}
