package com.spring.springboot.services.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.CourseDto;
import com.spring.springboot.entities.Course;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.repository.CoursesRepository;
import com.spring.springboot.services.CourseService;
import com.spring.springboot.utils.MapperUtils;

@Service
@Transactional(readOnly = false)
public class CourseServiceImplementation implements CourseService {
	
	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImplementation.class);
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private MapperUtils mapper;
	
	@Override
	@Transactional(readOnly = true)
	public CourseDto findById(Integer id) throws ObjNotFoundException {
		if(!coursesRepository.findById(id).isPresent()) 
			throw new ObjNotFoundException("No courses of id " + id + " has been found.");
		return mapper.map(coursesRepository.findById(id).get(), CourseDto.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public CourseDto findByName(String name) throws ObjNotFoundException {
		Course course = coursesRepository.findByName(name);
		if(course == null)
			throw new ObjNotFoundException("Course of name " + name + " was not found." );
		return mapper.map(course, CourseDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseDto> findAll() throws EmptyListException {
		List<Course> courses = coursesRepository.findAll();
		if (courses.isEmpty())
			throw new EmptyListException("No courses were found");
		logger.debug(courses.size() + " courses founded.");
		return mapper.mapAll(courses, CourseDto.class);
	}
	
	@Override
	public CourseDto save(CourseDto dto) throws InvalidOperationException {
		if(dto.getId()!=null)
			throw new InvalidOperationException("Errore! Stai tentando di fare un update ma devi fare un insert!");
		Course course = mapper.map(dto, Course.class);
		Course insert = coursesRepository.save(course);
		return mapper.map(insert, CourseDto.class);
	}

	@Override
	public CourseDto update(CourseDto dto) throws InvalidOperationException, ObjNotFoundException {
		//controllo che ci sia un id da cercare
		if(dto.getId() == null)
			throw new InvalidOperationException("Errore! Stai tentando di fare un insert ma devi fare un update!");
		//controllo che questo id sia effettivamente presente nel db
		if(!coursesRepository.findById(dto.getId()).isPresent())
			throw new ObjNotFoundException("No courses of id " + dto.getId() + " has been found.");
		Course course = mapper.map(dto, Course.class);
		Course update = coursesRepository.save(course);
		return mapper.map(update, CourseDto.class);
	}

	@Override
	public CourseDto delete(CourseDto dto) throws ObjNotFoundException {
		if(!coursesRepository.findById(dto.getId()).isPresent())
			throw new ObjNotFoundException("No courses of id " + dto.getId() + " has been found.");
		Course course = mapper.map(dto, Course.class);
		coursesRepository.delete(course);
		return dto;
	}

}
