package com.spring.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.springboot.entities.Course;

public interface CoursesRepository extends JpaRepository<Course, Integer>{
	
	@Query(value = "SELECT * FROM courses c WHERE c.name = :name",
			nativeQuery=true)
	public Course findByName(@Param("name") String name);

}
