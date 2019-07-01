package com.spring.springboot.dto;

import java.io.Serializable;

public class ExamsDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ExamsIdDto examsId;
	private Integer evaluation;
	
	public ExamsDto() {}

	public ExamsDto(ExamsIdDto examsId, Integer evaluation) {
		this.examsId = examsId;
		this.evaluation = evaluation;
	}

	public ExamsIdDto getExamsId() {
		return examsId;
	}

	public void setExamsId(ExamsIdDto examsId) {
		this.examsId = examsId;
	}

	public Integer getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}
	
}
