package com.spring.springboot.services;

import com.spring.springboot.dto.ImagesDto;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.ImageInsertionException;

import javassist.tools.rmi.ObjectNotFoundException;

public interface ImagesService {

	public ImagesDto findById(Integer student) throws ObjectNotFoundException;
	public ImagesDto save(ImagesDto dto) throws ImageInsertionException;
	public ApiResponse delete(Integer dto) throws ObjectNotFoundException;
	public ImagesDto update(ImagesDto dto) throws ObjectNotFoundException;
}
