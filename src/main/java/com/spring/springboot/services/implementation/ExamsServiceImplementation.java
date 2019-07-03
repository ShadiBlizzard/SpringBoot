package com.spring.springboot.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.ExamsDto;
import com.spring.springboot.entities.Course;
import com.spring.springboot.entities.Exams;
import com.spring.springboot.entities.ExamsId;
import com.spring.springboot.entities.Student;
import com.spring.springboot.exceptions.ExamAlreadyRegisteredException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.exceptions.UnexistingExamException;
import com.spring.springboot.repository.CoursesRepository;
import com.spring.springboot.repository.ExamsRepository;
import com.spring.springboot.repository.StudentRepository;
import com.spring.springboot.services.ExamsService;
import com.spring.springboot.utils.MapperUtils;
import com.spring.springboot.utils.StringUtils;


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
	public ExamsDto save(ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException, UnexistingExamException {
		Exams exam = persistExams(dto.getExamsId().getStudent().getId(), dto.getExamsId().getCourse().getName(), dto.getEvaluation(), true);
		return mapper.map(exam, ExamsDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public ExamsDto update(ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException, UnexistingExamException{
		Exams exam = persistExams(dto.getExamsId().getStudent().getId(), dto.getExamsId().getCourse().getName(), dto.getEvaluation(), false);
		return mapper.map(exam, ExamsDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public ExamsDto delete(ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException, UnexistingExamException{
		Exams exam = getExams(dto.getExamsId().getStudent().getId(), dto.getExamsId().getCourse().getName(), dto.getEvaluation(), false);
		if(exam == null)
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_EXAM, dto.getExamsId().getCourse(), dto.getExamsId().getStudent()));
		examsRepository.delete(exam);
		return dto;
	}

	private Exams persistExams(Integer student, String course, Integer evaluation, boolean isExisting) throws  ObjNotFoundException, ExamAlreadyRegisteredException, UnexistingExamException{
		Exams exam = getExams(student, course, evaluation, isExisting);
		if(exam == null)
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_EXAM, course, student));
		return examsRepository.save(exam);	
	}	
	
	private Exams getExams(Integer student, String course, Integer evaluation, boolean isExisting) throws ObjNotFoundException, ExamAlreadyRegisteredException, UnexistingExamException {
		Optional<Student> studentExams = studentRepository.findById(student);
		if(!studentExams.isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.STUDENT, "id", student));
		Course courseExams = coursesRepository.findByName(course);
		if(courseExams == null)
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_NAME, StringUtils.COURSE, "name", course));
		ExamsId examsId = new ExamsId(studentExams.get(), courseExams);
		
		if(examsRepository.existsById(examsId)==isExisting) 
			if(isExisting)
				throw new ExamAlreadyRegisteredException(String.format(StringUtils.EXAM_ALREADY_REGISTERED, course, student));
			else 
				throw new UnexistingExamException(String.format(StringUtils.EXAM_UNEXISTING, course));

		
		return new Exams(examsId, evaluation);
		
	}
	
}
