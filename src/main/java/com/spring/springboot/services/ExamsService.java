package com.spring.springboot.services;

import com.spring.springboot.dto.ExamsDto;
import com.spring.springboot.exceptions.ExamAlreadyRegisteredException;
import com.spring.springboot.exceptions.ObjNotFoundException;

public interface ExamsService {
	
	public ExamsDto save(ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException;
	public ExamsDto update(ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException;
	public ExamsDto delete(ExamsDto dto) throws ObjNotFoundException, ExamAlreadyRegisteredException;

}
