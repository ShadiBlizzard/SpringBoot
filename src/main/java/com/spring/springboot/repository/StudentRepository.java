package com.spring.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.springboot.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	

}
