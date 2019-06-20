package com.spring.springboot.services;

import com.spring.springboot.entities.Exams;

public interface ExamsService {
	
	public Exams save(Integer student, String course, Integer evaluation) throws Exception;
	public Exams update(Integer student, String course, Integer evaluation) throws Exception;
	public void delete(Integer student, String course) throws Exception;

}
