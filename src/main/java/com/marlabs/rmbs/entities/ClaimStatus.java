package com.marlabs.rmbs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claimstatus_master")
public class ClaimStatus {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private int id;
	  
	  private String claimStatusCode;
	  private String claimStatusDesc;
	  
	  
	  
	  
	  //Getter And Setter
	public String getClaimStatusCode() {
		return claimStatusCode;
	}
	public void setClaimStatusCode(String claimStatusCode) {
		this.claimStatusCode = claimStatusCode;
	}
	public String getClaimStatusDesc() {
		return claimStatusDesc;
	}
	public void setClaimStatusDesc(String claimStatusDesc) {
		this.claimStatusDesc = claimStatusDesc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
