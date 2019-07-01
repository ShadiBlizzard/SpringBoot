package com.spring.springboot.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.ExamsDto;
import com.spring.springboot.entities.Course;
import com.spring.springboot.entities.Exams;
import com.spring.springboot.entities.ExamsId;
import com.spring.springboot.entities.Student;
import com.spring.springboot.repository.CoursesRepository;
import com.spring.springboot.repository.ExamsRepository;
import com.spring.springboot.repository.StudentRepository;
import com.spring.springboot.services.ExamsService;
import com.spring.springboot.utils.MapperUtils;


@Service
public class ExamsServiceImplementation implements ExamsService {
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private ExamsRepository examsRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired 
	private MapperUtils mapper;

	@Override
	@Transactional(readOnly = false)
	public ExamsDto save(ExamsDto dto) {
		Exams exam = persistExams(dto.getExamsId().getStudent().getId(), dto.getExamsId().getCourse().getName(), dto.getEvaluation(), true);
		return mapper.map(exam, ExamsDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public ExamsDto update(ExamsDto dto){
		Exams exam = persistExams(dto.getExamsId().getStudent().getId(), dto.getExamsId().getCourse().getName(), dto.getEvaluation(), false);
		return mapper.map(exam, ExamsDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public ExamsDto delete(ExamsDto dto){
		examsRepository.delete(getExams(dto.getExamsId().getStudent().getId(), dto.getExamsId().getCourse().getName(), dto.getEvaluation(), false));
		return dto;
	}

	private Exams persistExams(Integer student, String course, Integer evaluation, boolean isExisting) throws IllegalStateException{
		return examsRepository.save(getExams(student, course, evaluation, isExisting));	
	}	
	
	private Exams getExams(Integer student, String course, Integer evaluation, boolean isExisting) throws IllegalStateException {
		Student studentExams = studentRepository.findById(student).get();
		Course courseExams = coursesRepository.findByName(course);
		ExamsId examsId = new ExamsId(studentExams, courseExams);
		
		if(examsRepository.existsById(examsId)==isExisting) throw new IllegalStateException();
		
		Exams exams = new Exams(examsId, evaluation);
		
		return exams;
	}
	
}
