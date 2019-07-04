package com.spring.springboot.services;

import com.spring.springboot.dto.ImagesDto;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;


public interface ImagesService {

	public ApiResponse findById(Integer student) throws ObjNotFoundException;
	public ApiResponse save(ImagesDto dto) throws InvalidOperationException;
	public ApiResponse delete(Integer dto) throws ObjNotFoundException;
	public ApiResponse update(ImagesDto dto) throws ObjNotFoundException;
}
