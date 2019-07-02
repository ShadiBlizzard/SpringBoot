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
	public ExamsDto save(ExamsDto dto) throws IllegalStateException{
		Exams exam = persistExams(dto.getExamsId().getStudent().getId(), dto.getExamsId().getCourse().getName(), dto.getEvaluation(), true);
		if(exam == null)
			throw new IllegalStateException();
		return mapper.map(exam, ExamsDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public ExamsDto update(ExamsDto dto) throws IllegalStateException{
		Exams exam = persistExams(dto.getExamsId().getStudent().getId(), dto.getExamsId().getCourse().getName(), dto.getEvaluation(), false);
		if(exam == null)
			throw new IllegalStateException();
		return mapper.map(exam, ExamsDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public ExamsDto delete(ExamsDto dto) throws IllegalStateException{
		Exams exam = getExams(dto.getExamsId().getStudent().getId(), dto.getExamsId().getCourse().getName(), dto.getEvaluation(), false);
		if(exam == null)
			throw new IllegalStateException();
		examsRepository.delete(exam);
		return dto;
	}

	private Exams persistExams(Integer student, String course, Integer evaluation, boolean isExisting) throws IllegalStateException{
		Exams exam = getExams(student, course, evaluation, isExisting);
		if(exam == null)
			throw new IllegalStateException();
		return examsRepository.save(exam);	
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
