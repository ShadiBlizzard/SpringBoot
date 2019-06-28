package com.spring.springboot.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.entities.Course;
import com.spring.springboot.entities.Exams;
import com.spring.springboot.entities.ExamsId;
import com.spring.springboot.entities.Student;
import com.spring.springboot.repository.CoursesRepository;
import com.spring.springboot.repository.ExamsRepository;
import com.spring.springboot.repository.StudentRepository;
import com.spring.springboot.services.ExamsService;


@Service
public class ExamsServiceImplementation implements ExamsService {
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private ExamsRepository examsRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional(readOnly = false)
	public Exams save(Integer student, String course, Integer evaluation) throws Exception{
		return persistExams(student, course, evaluation, true);
	}

	@Override
	@Transactional(readOnly = false)
	public Exams update(Integer student, String course, Integer evaluation) throws Exception{
		return persistExams(student, course, evaluation, false);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Integer student, String course) throws Exception {
		examsRepository.delete(getExams(student,course, null, false));
	}

	private Exams persistExams(Integer student, String course, Integer evaluation, boolean isExisting) throws Exception{
		return examsRepository.save(getExams(student, course, evaluation, isExisting));	
	}	
	
	private Exams getExams(Integer student, String course, Integer evaluation, boolean isExisting) throws Exception {
		Student studentExams = studentRepository.findById(student).get();
		Course courseExams = coursesRepository.findByName(course);
		ExamsId examsId = new ExamsId(studentExams, courseExams);
		
		if(examsRepository.existsById(examsId)==isExisting) throw new Exception();
		
		Exams exams = new Exams(examsId, evaluation);
		
		return exams;
	}
	
}
