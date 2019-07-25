package com.marlabs.rmbs.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "per_diem_master")
public class PerDiemMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private Date effectiveDate;
    private int claimTypeId;
    private String category;
    private String location;
    private int grade;
    private Double perDiemAmnt;
    private String currency;
    
    public int getId() {
			return id;
		}
	public void setId(int id) {
		this.id = id;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public int getClaimTypeId() {
		return claimTypeId;
	}
	public void setClaimTypeId(int claimTypeId) {
		this.claimTypeId = claimTypeId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Double getPerDiemAmnt() {
		return perDiemAmnt;
	}
	public void setPerDiemAmnt(Double perDiemAmnt) {
		this.perDiemAmnt = perDiemAmnt;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	  
}
