package com.spring.springboot.entities;

import javax.persistence.AssociationOverride;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/*****
 * 
 * VIEW CAREER
 *
 */

@Entity
@Immutable
@Table(name="career")
@AssociationOverride(name="careerId.studentname", joinColumns = @JoinColumn(name="studentname"))
@AssociationOverride(name="careerId.studentsurname", joinColumns = @JoinColumn(name="studentsurname"))
@AssociationOverride(name="careerId.coursename", joinColumns = @JoinColumn(name="coursename"))
@AssociationOverride(name="careerId.evaluation", joinColumns = @JoinColumn(name="evaluation"))
public class Career {

	private CareerId careerId;
	
	public Career() {}
	
	public Career(CareerId careerId) {
		this.careerId = careerId;
	}

	@EmbeddedId
	public CareerId getCareerId() {
		return careerId;
	}

	public void setCareerId(CareerId careerId) {
		this.careerId = careerId;
	}
	
	
}
