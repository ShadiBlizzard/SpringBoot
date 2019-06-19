package com.spring.springboot.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springboot.entities.Course;
import com.spring.springboot.repository.CoursesRepository;
import com.spring.springboot.services.CourseService;

@Service
public class CourseServiceImplementation implements CourseService {
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Override
	public String findByName(String name) {
		Course course = coursesRepository.findByName(name);
		
		if (course == null) 
			return "Sorry, the course name you inserted doesn't exist";
		return "Course id: " + course.getId() + " name: " + course.getName() + " description: " + course.getDescription();
	}

	@Override
	public String findDescriptionByName(String name) {
		String desc = coursesRepository.findDescriptionByName(name);
		if (desc == null)
			return "No description found for the course";
		return desc;
	}

	@Override
	public List<String> findAll() {
		List<Course> courses = coursesRepository.findAll();
		
		List<String> found = new ArrayList<>();
		for(Course c: courses) 
			found.add(c.getName() + ": " + c.getDescription());
		
		return found;
	}
	
	@Override
	public String save(String name, String description) {
		if(name!= null && description!= null) {
			Course course = new Course(name, description);
			coursesRepository.save(course);
			return "New course inserted";
		}
		else 
			return "Parameters not valid";
	}

}
