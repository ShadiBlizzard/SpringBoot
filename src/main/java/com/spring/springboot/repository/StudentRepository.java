package com.spring.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.springboot.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	List<Student> findByNameAndSurname(String name, String Surname);
	
}
