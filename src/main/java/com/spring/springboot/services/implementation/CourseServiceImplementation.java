package com.spring.springboot.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.entities.Course;
import com.spring.springboot.repository.CoursesRepository;
import com.spring.springboot.services.CourseService;

@Service
public class CourseServiceImplementation implements CourseService {
	
	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImplementation.class);
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Override
	@Transactional(readOnly = true)
	public String findByName(String name) {
		Course course = coursesRepository.findByName(name);
		logger.debug("With name " + name + " found course " + ((course==null)?"NULL":course.getName()));
		if (course == null) 
			return "Sorry, the course name you inserted doesn't exist";
		return "Course id: " + course.getId() + " name: " + course.getName() + " description: " + course.getDescription();
	}

	@Override
	@Transactional(readOnly = true)
	public String findDescriptionByName(String name) {
		String desc = coursesRepository.findDescriptionByName(name);
		if (desc == null)
			return "No description found for the course";
		return desc;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> findAll() {
		List<Course> courses = coursesRepository.findAll();
		logger.debug(courses.size() + " courses founded.");
		List<String> found = new ArrayList<>();
		for(Course c: courses) 
			found.add(c.getName() + ": " + c.getDescription());
		
		return found;
	}
	
	@Override
	@Transactional(readOnly = false)
	public String save(String name, String description) {
		if(coursesRepository.findByName(name)== null && name!= null && description!= null) {
			Course course = new Course(name, description);
			if(coursesRepository.save(course)!= null)
				return "New course inserted";
			return "Something went wrong";
		}
		else 
			return "Parameters not valid";
	}

	@Override
	@Transactional(readOnly = false)
	public String update(String name, String description) {
		Course course = coursesRepository.findByName(name);
		if(course!=null) {
			course.setDescription(description);
			coursesRepository.save(course);
			return "Update successfull";
		}
		
		return "Something went wrong!";
	}

	@Override
	@Transactional(readOnly = false)
	public String delete(String name) {
		Course course = coursesRepository.findByName(name);
		if(course != null) {
			coursesRepository.deleteById(course.getId());
			return "Course deleted successfully";
		}
		
		return "The course you are tryng to delete doesn't exist";
	}

}
