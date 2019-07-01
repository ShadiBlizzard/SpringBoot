package com.spring.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.springboot.entities.Course;

public interface CoursesRepository extends JpaRepository<Course, Integer>{

	public Course findByName(String name);

}
