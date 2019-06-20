package com.spring.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.springboot.entities.Career;
import com.spring.springboot.entities.CareerId;

public interface CareerRepository extends JpaRepository<Career, CareerId>{

}
