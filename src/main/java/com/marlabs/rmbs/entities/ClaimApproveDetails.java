package com.marlabs.rmbs.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "claimapprove_details")
public class ClaimApproveDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private ClaimSubmitHeader claimHeaderId;
	@OneToOne
	private UserMaster approverId;
	@OneToOne
	@NotNull
	private ClaimStatus claimStatus;
	private Date modifiedDate;
	private String remarks;
	private int sequenceNumber;
	private String approveType;
	private Double approvedAmount;
	private Integer approveCurrencyId;
	
	public ClaimApproveDetails(){}
	
	public ClaimApproveDetails(ClaimSubmitHeader claimHeaderId,UserMaster approverId, ClaimStatus claimStatus) {
		this.claimHeaderId = claimHeaderId;
		this.approverId = approverId;
		this.claimStatus = claimStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClaimSubmitHeader getClaimHeaderId() {
		return claimHeaderId;
	}

	public void setClaimHeaderId(ClaimSubmitHeader claimHeaderId) {
		this.claimHeaderId = claimHeaderId;
	}

	public UserMaster getApproverId() {
		return approverId;
	}

	public void setApproverId(UserMaster approverId) {
		this.approverId = approverId;
	}

	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getApproveType() {
		return approveType;
	}

	public void setApproveType(String approveType) {
		this.approveType = approveType;
	}

	public Double getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(Double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
    
	public Integer getApproveCurrencyId() {
		return approveCurrencyId;
	}
	public void setApproveCurrencyId(Integer approveCurrencyId) {
		this.approveCurrencyId = approveCurrencyId;
	}

	@Override
	public String toString() {
		return "ClaimApproveDetails [id=" + id + ", claimHeaderId=" + claimHeaderId + ", approverId=" + approverId
				+ ", claimStatus=" + claimStatus + ", modifiedDate=" + modifiedDate + ", remarks=" + remarks
				+ ", sequenceNumber=" + sequenceNumber + ", approveType=" + approveType + ", approvedAmount="
				+ approvedAmount + ", approveCurrencyId=" + approveCurrencyId + "]";
	}

	
	

}
