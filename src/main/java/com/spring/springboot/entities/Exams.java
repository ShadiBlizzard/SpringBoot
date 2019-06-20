package com.spring.springboot.entities;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "exams")
@AssociationOverrides({ @AssociationOverride(name = "examsId.student", joinColumns = @JoinColumn(name = "idstudent")),
		@AssociationOverride(name = "examsId.course", joinColumns = @JoinColumn(name = "idcourse")) })
public class Exams {

	private ExamsId examsId; // = new ExamsId();
	
	@NotNull
	private Integer evaluation;
	
	public Exams() {}
	
	public Exams(ExamsId examsId, @NotNull Integer evaluation) {
		this.examsId = examsId;
		this.evaluation = evaluation;
	}

	@EmbeddedId
	public ExamsId getExamsId() {
		return examsId;
	}

	public void setExamsId(ExamsId examsId) {
		this.examsId = examsId;
	}

	public Integer getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}
	
	

}
