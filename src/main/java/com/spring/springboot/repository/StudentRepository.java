package com.spring.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.springboot.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	Page<Student> findAll(Pageable pageable);
	List<Student> findByNameAndSurname(String name, String surname, Pageable pageable);
	
}
