package com.marlabs.rmbs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "claimapprovefwd_employees")

public class ClaimApproveFwdEmployees {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@OneToOne
	@NotNull
	private UserMaster approveFwdFromEmployeeId;
	
	@OneToOne
	@NotNull
	private UserMaster approveFwdToEmployeeId;
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserMaster getApproveFwdFromEmployeeId() {
		return approveFwdFromEmployeeId;
	}

	public void setApproveFwdFromEmployeeId(UserMaster approveFwdFromEmployeeId) {
		this.approveFwdFromEmployeeId = approveFwdFromEmployeeId;
	}

	public UserMaster getApproveFwdToEmployeeId() {
		return approveFwdToEmployeeId;
	}

	public void setApproveFwdToEmployeeId(UserMaster approveFwdToEmployeeId) {
		this.approveFwdToEmployeeId = approveFwdToEmployeeId;
	}
	
	
	
	
	

}
