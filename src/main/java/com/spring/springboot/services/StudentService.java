package com.spring.springboot.services;

import java.util.List;




public interface StudentService {
	
	public String findStudentById(int id);
	public List<String> findAll();
	public String save(String name, String surname);
	public String update(int id, String name, String surname);
	public String delete(int id);

}
