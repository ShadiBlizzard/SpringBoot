package com.spring.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.springboot.entities.Career;
import com.spring.springboot.entities.CareerId;

public interface CareerRepository extends JpaRepository<Career, CareerId>{
	
	@Query (value = "SELECT * FROM CAREER WHERE studentname = :name and studentsurname = :surname", nativeQuery = true)
	public Career findCareerByStudent(@Param("name") String name, @Param("surname") String surname);

}
