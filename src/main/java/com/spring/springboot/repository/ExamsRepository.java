package com.spring.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.springboot.entities.Exams;
import com.spring.springboot.entities.ExamsId;

public interface ExamsRepository extends JpaRepository<Exams, ExamsId>{

}
