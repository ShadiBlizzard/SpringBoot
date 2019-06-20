package com.spring.springboot.services.implementation;

import java.util.ArrayList;
import java.util.List;
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
			return "Student found: " + student.getName() + " " + student.getSurname();
		return "Student not found";
		
		
	}

	@Override
	public List<String> findAll() {
		List<Student> students = studentRepository.findAll();
		
		List<String> studentsNames = new ArrayList<>();
		for(Student student: students)
			studentsNames.add("Name: " + student.getName() + " Surname: " + student.getSurname());
		return studentsNames;

		
	}

	@Override
	public String save(String name, String surname) {
		Student student = new Student();
		student.setName(name);
		student.setSurname(surname);
		
		if(studentRepository.save(student)!= null)
			return "New student inserted";
		return "Something went wrong";
		
		
		
	}

	@Override
	public String update(int id, String name, String surname) {
		Student student = studentRepository.findById(id);
		if(student!= null) {
			if(name!=null)
				student.setName(name);
			if(surname!=null)
				student.setSurname(surname);
			if(studentRepository.save(student)!=null)
				return "Update successfull";
			return "Something went wrong!";
			
		}
		return "Student not found!";
	}

	@Override
	public String delete(int id) {
		Student student = studentRepository.findById(id);
		if(student!=null) {
			studentRepository.delete(student);
			return "Deletion successfull";
		}
		
		return "Student not found!";
	}

}
