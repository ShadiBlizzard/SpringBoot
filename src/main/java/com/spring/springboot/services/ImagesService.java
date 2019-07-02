package com.spring.springboot.services;

import com.spring.springboot.dto.ImagesDto;

public interface ImagesService {

	public ImagesDto findById(Integer student);
	public ImagesDto save(ImagesDto dto);
	public ImagesDto delete(ImagesDto dto);
	public ImagesDto update(ImagesDto dto);
}
