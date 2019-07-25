package com.marlabs.rmbs.request.vo;

import java.util.Date;

public class ApprovelDetailsVo {
	
	
	private String claimStatus;
	private String managerName;
	private String remarks;
	private String modifiedDate;
	private String approveType;
	private Integer approveCurrencyId;
	private Double approvedAmount;

	public ApprovelDetailsVo()
	{
		
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getApproveType() {
		return approveType;
	}

	public void setApproveType(String approveType) {
		this.approveType = approveType;
	}

	public Integer getApproveCurrencyId() {
		return approveCurrencyId;
	}

	public void setApproveCurrencyId(Integer approveCurrencyId) {
		this.approveCurrencyId = approveCurrencyId;
	}

	public Double getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(Double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	
	
	
	
	

	
}	
