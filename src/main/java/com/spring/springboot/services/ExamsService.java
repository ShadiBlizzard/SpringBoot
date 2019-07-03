package com.spring.springboot.services;

import com.spring.springboot.dto.ExamsDto;
import com.spring.springboot.exceptions.ExamAlreadyRegisteredException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.exceptions.UnexistingExamException;

public interface ExamsService {
	
	public ExamsDto save(ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException, UnexistingExamException;
	public ExamsDto update(ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException, UnexistingExamException;
	public ExamsDto delete(ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException, UnexistingExamException;

}
