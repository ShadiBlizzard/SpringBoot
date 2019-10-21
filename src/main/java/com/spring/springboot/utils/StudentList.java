package com.spring.springboot.utils;

import java.util.ArrayList;
import java.util.List;

import com.spring.springboot.entities.Student;

public class StudentList {
	private static List<Student> students = new ArrayList<Student>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -278528594470018560L;

		{
			add(new Student(1, "Pinco", "Pallino"));
			add(new Student(2, "Luke", "Skywalker"));
			add(new Student(3, "Daenerys", "Targaryen"));
			add(new Student(4, "Arya", "Stark"));
		}
	};

	public static List<Student> getStudents() {
		return students;
	}

	public static void setStudents(List<Student> students) {
		StudentList.students = students;
	}
	
	public static Student getStudentById(int id) {
		for(Student s: students) {
			if(s.getId() == id)
				return s;
		}
		throw new IllegalArgumentException("Id + "+ id + " not found");
	}
	
	
	
}
