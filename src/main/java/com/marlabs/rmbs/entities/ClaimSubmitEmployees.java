package com.marlabs.rmbs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "claimsubmit_employees")

public class ClaimSubmitEmployees {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@OneToOne
	@NotNull
	private UserMaster submitFromEmployeeId;
	
	@OneToOne
	@NotNull
	private UserMaster submitToEmployeeId;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserMaster getSubmitFromEmployeeId() {
		return submitFromEmployeeId;
	}

	public void setSubmitFromEmployeeId(UserMaster submitFromEmployeeId) {
		this.submitFromEmployeeId = submitFromEmployeeId;
	}

	public UserMaster getSubmitToEmployeeId() {
		return submitToEmployeeId;
	}

	public void setSubmitToEmployeeId(UserMaster submitToEmployeeId) {
		this.submitToEmployeeId = submitToEmployeeId;
	}
	
	
	
	
	
	
	
}
