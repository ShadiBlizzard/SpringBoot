package com.spring.springboot.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.springboot.entities.Career;
import com.spring.springboot.entities.CareerId;

public interface CareerRepository extends JpaRepository<Career, CareerId>{
	
	public Page<Career> findAll(Pageable pageable);
	
	@Query (value = "SELECT * FROM CAREER WHERE studentname = :name AND studentsurname = :surname", nativeQuery = true)
	public List<Career> findCareerByStudent(@Param("name") String name, @Param("surname") String surname, Pageable pageable);
	
	@Query (value = "SELECT * FROM CAREER WHERE coursename = :course", nativeQuery = true)
	public List<Career> findCareerByCourse(@Param("course") String course, Pageable pageable);

	@Query (value = "SELECT * FROM CAREER WHERE coursename = :course AND evaluation = :evaluation ", nativeQuery = true)
	public List<Career> findCareerByCourseAndEvaluation(@Param("course") String course, @Param("evaluation")Integer evaluation, Pageable pageable);
}
