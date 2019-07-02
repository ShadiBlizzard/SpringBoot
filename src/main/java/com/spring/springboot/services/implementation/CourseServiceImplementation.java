package com.spring.springboot.services.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.CourseDto;
import com.spring.springboot.entities.Course;
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
	public CourseDto findById(Integer id) {
		Course course = coursesRepository.findById(id).get();
		return mapper.map(course, CourseDto.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public CourseDto findByName(String name) {
		Course course = coursesRepository.findByName(name);
		return mapper.map(course, CourseDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseDto> findAll() {
		List<Course> courses = coursesRepository.findAll();
		logger.debug(courses.size() + " courses founded.");
		return mapper.mapAll(courses, CourseDto.class);
	}
	
	@Override
	public CourseDto save(CourseDto dto) {
		if(dto.getId()!=null)
			throw new IllegalStateException("Errore! Stai tentando di fare un update ma devi fare un insert!");
		Course course = mapper.map(dto, Course.class);
		Course insert = coursesRepository.save(course);
		return mapper.map(insert, CourseDto.class);
	}

	@Override
	public CourseDto update(CourseDto dto) {
		//controllo che ci sia un id da cercare
		if(dto.getId() == null)
			throw new IllegalStateException("Errore! Stai tentando di fare un insert ma devi fare un update!");
		//controllo che questo id sia effettivamente presente nel db
		if(findById(dto.getId()).getId() == null)
			throw new IllegalStateException("Errore! Oggetto non trovato sul db!");
		Course course = mapper.map(dto, Course.class);
		Course update = coursesRepository.save(course);
		return mapper.map(update, CourseDto.class);
	}

	@Override
	public CourseDto delete(CourseDto dto) {
		if(findById(dto.getId()).getId() == null)
			throw new IllegalStateException("Errore! Oggetto non trovato sul db!");
		Course course = mapper.map(dto, Course.class);
		coursesRepository.delete(course);
		return dto;
	}

}
