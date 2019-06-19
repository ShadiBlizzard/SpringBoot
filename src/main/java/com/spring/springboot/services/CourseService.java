package com.spring.springboot.services;

import java.util.List;


public interface CourseService {
	
	public String findByName(String name);
	public String findDescriptionByName(String name);
	public List<String> findAll();
	public String save(String name, String description);
	public String update(String name, String description);
}
