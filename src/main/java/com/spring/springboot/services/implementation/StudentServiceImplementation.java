package com.spring.springboot.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springboot.entities.Student;
import com.spring.springboot.repository.StudentRepository;
import com.spring.springboot.services.StudentService;

@Service
public class StudentServiceImplementation implements StudentService {
	
	@Autowired
	public StudentRepository studentRepository;

	@Override
	public String findStudentById(int id) {
		Student student = (Student) studentRepository.findById(id);
		
		if(student!= null) 
			return "Student found:" + student.getName() + " " + student.getSurname();
		return "Student not found";
		
		
	}

}
