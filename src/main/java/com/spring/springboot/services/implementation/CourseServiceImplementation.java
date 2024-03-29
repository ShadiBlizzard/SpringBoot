package com.spring.springboot.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.CourseDto;
import com.spring.springboot.entities.Course;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.EmptyListException;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.repository.CoursesRepository;
import com.spring.springboot.services.CourseService;
import com.spring.springboot.utils.MapperUtils;
import com.spring.springboot.utils.StringUtils;

@Service
@Transactional(readOnly = false)
public class CourseServiceImplementation implements CourseService {
		
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private MapperUtils mapper;
	
	@Override
	@Transactional(readOnly = true)
	public ApiResponse findById(Integer id) throws ObjNotFoundException {
		Optional<Course> course = coursesRepository.findById(id);
		if(course.isPresent()) 
			return new ApiResponse(HttpStatus.OK, mapper.map(course.get(), CourseDto.class));
		else 
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.COURSE, "id", id));

	}
	
	@Override
	@Transactional(readOnly = true)
	public ApiResponse findByName(String name) throws ObjNotFoundException {
		Course course = coursesRepository.findByName(name);
		if(course == null)
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_NAME, StringUtils.COURSE, "name", name));
		return new ApiResponse(HttpStatus.FOUND, mapper.map(course, CourseDto.class));
	}

	@Override
	@Transactional(readOnly = true)
	public ApiResponse findAll(Pageable pageable) throws EmptyListException {
		List<Course> courses = coursesRepository.findAll(pageable).getContent();
		if (courses.isEmpty())
			throw new EmptyListException(String.format(StringUtils.EMPTY_LIST, StringUtils.COURSE + "s"));
		return new ApiResponse(HttpStatus.FOUND, mapper.mapAll(courses, CourseDto.class));

	}
	
	@Override
	public ApiResponse save(CourseDto dto) throws InvalidOperationException {
		if(dto.getId()!=null)
			throw new InvalidOperationException(StringUtils.INVALID_UPDATE_OP);
		Course course = mapper.map(dto, Course.class);
		Course insert = coursesRepository.save(course);
		return new ApiResponse(HttpStatus.OK, mapper.map(insert, CourseDto.class));
	}

	@Override
	public ApiResponse update(CourseDto dto) throws InvalidOperationException, ObjNotFoundException {
		//controllo che ci sia un id da cercare
		if(dto.getId() == null)
			throw new InvalidOperationException(StringUtils.INVALID_INSERT_OP);
		//controllo che questo id sia effettivamente presente nel db
		if(!coursesRepository.findById(dto.getId()).isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.COURSE, "id", dto.getId()));
		Course course = mapper.map(dto, Course.class);
		coursesRepository.save(course);
		return new ApiResponse(HttpStatus.OK, String.format(StringUtils.UPDATE_SUCCESS, course.getClass().getSimpleName()));
	}

	@Override
	public ApiResponse delete(Integer dto) throws ObjNotFoundException {
		Optional<Course> course = coursesRepository.findById(dto);
		if(!course.isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.COURSE, "id", dto));
		Course courseDto = mapper.map(course.get(), Course.class);
		coursesRepository.delete(courseDto);
		return new ApiResponse(HttpStatus.OK, String.format(StringUtils.DELETE_SUCCESS, course.getClass().getSimpleName()));
	}
	
	@Override
	public ApiResponse count() {
		Long count = coursesRepository.count();
		return new ApiResponse(HttpStatus.OK, count);
	}

}
