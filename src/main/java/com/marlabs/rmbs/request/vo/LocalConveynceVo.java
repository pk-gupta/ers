package com.marlabs.rmbs.request.vo;

import java.util.Date;

public class LocalConveynceVo{
	private Integer id;
	private Integer claimSubmitHeaderId;
	private Date billDate;
	private Double rate;
	private String mode;
	private Double distance;
	private String remarks;
	
	
	public LocalConveynceVo(){
		
	}
	
	public LocalConveynceVo(Integer id) {
		super();
		this.id = id;
	}
	public LocalConveynceVo(Integer id, Integer claimSubmitHeaderId, Date billDate, Double rate, String mode,
			Double distance, String remarks) {
		super();
		this.id = id;
		this.claimSubmitHeaderId = claimSubmitHeaderId;
		this.billDate = billDate;
		this.rate = rate;
		this.mode = mode;
		this.distance = distance;
		this.remarks = remarks;
	}
	//Generate Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClaimSubmitHeaderId() {
		return claimSubmitHeaderId;
	}
	public void setClaimSubmitHeaderId(Integer claimSubmitHeaderId) {
		this.claimSubmitHeaderId = claimSubmitHeaderId;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

	
}
