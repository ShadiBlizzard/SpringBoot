package com.spring.springboot.services;

import com.spring.springboot.entities.Images;

public interface ImagesService {

	public Images findById(Integer student);
	public Images save(Integer student, byte[] image);
	public String delete(int student);
	public Images update(int student, byte[] image);
}
