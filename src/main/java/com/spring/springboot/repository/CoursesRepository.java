package com.spring.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.springboot.entities.Course;

public interface CoursesRepository extends JpaRepository<Course, Integer>{

	public Course findByName(String name);
	public Page<Course> findAll(Pageable pageable);

}
