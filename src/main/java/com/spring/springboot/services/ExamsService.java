package com.spring.springboot.services;

import com.spring.springboot.dto.ExamsDto;

public interface ExamsService {
	
	public ExamsDto save(ExamsDto dto);
	public ExamsDto update(ExamsDto dto);
	public ExamsDto delete(ExamsDto dto);

}
