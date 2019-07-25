package com.marlabs.rmbs.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "claimsubmit_header")

public class ClaimSubmitHeader {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private String claimNo;
	private Date claimDate;
	
	@OneToOne
	@JoinColumn(referencedColumnName="id")
	@NotNull
	private UserMaster requestingUserId;
	
	@OneToOne
	@JoinColumn(referencedColumnName="id")
	@NotNull
	private ClaimTypeMaster claimTypeId;
	
	@OneToOne
	@JoinColumn(referencedColumnName="id")
	@NotNull
	private CurrencyMaster currencyId;
	
	private int noOfREceipts;
	private Date periodFrom;
	private Date periodTo;
	private String project;
	@Column(name="remarks", length=500)
	private String remarks;
	private Double totalExpense;
	private Double claimAmount;
	private Boolean clientBillable;
	
	@ColumnDefault(value = "1")
	private int financeType;
	
	
	public int getFinanceType() {
		return financeType;
	}

	public void setFinanceType(int financeType) {
		this.financeType = financeType;
	}
	@ColumnDefault(value = "0")
	private int validatePersonId;

	public int getValidatePersonId() {
		return validatePersonId;
	}

	public void setValidatePersonId(int validatePersonId) {
		this.validatePersonId = validatePersonId;
	}
	@ColumnDefault(value = "0")
	private int forwardPersonId;
	
	public int getForwardPersonId() {
		return forwardPersonId;
	}

	public void setForwardPersonId(int forwardPersonId) {
		this.forwardPersonId = forwardPersonId;
	}
	@ColumnDefault(value = "0")
	private int secondLevelApproverId;
	
	public int getSecondLevelApproverId() {
		return secondLevelApproverId;
	}

	public void setSecondLevelApproverId(int secondLevelApproverId) {
		this.secondLevelApproverId = secondLevelApproverId;
	}
	@OneToOne
	@JoinColumn(referencedColumnName="id")
	private UserMaster approvedPersonId;
	
	@OneToOne
	@JoinColumn(referencedColumnName="id")
	private ClaimStatus finalStatus;
	
	
	 @OneToOne
	 private ProjectMaster projectMasterId;
	 
	  @Column(columnDefinition = "boolean default false", nullable = false)
	  private Boolean specialApprove=false;
	
	  private Date modifiedDate;
	  
	@ColumnDefault(value = "0")
	private double exchangeRate;

	


	public double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public ProjectMaster getProjectMasterId() {
		return projectMasterId;
	}

	public void setProjectMasterId(ProjectMaster projectMasterId) {
		this.projectMasterId = projectMasterId;
	}

	public UserMaster getApprovedPersonId() {
		return approvedPersonId;
	}

	public void setApprovedPersonId(UserMaster approvedPersonId) {
		this.approvedPersonId = approvedPersonId;
	}
	
	
	//Getters And Setters
	public ClaimSubmitHeader(){}
	
	public ClaimSubmitHeader(UserMaster requestingUserId,
			ClaimTypeMaster claimTypeId, CurrencyMaster currencyId, int noOfREceipts, Date periodFrom, Date periodTo,
			String project, String remarks, Double totalExpense, Double claimAmount) {
	
		this.requestingUserId = requestingUserId;
		this.claimTypeId = claimTypeId;
		this.currencyId = currencyId;
		this.noOfREceipts = noOfREceipts;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.project = project;
		this.remarks = remarks;
		this.totalExpense = totalExpense;
		this.claimAmount = claimAmount;
	}
	
	public Date getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}
	public int getNoOfREceipts() {
		return noOfREceipts;
	}
	public void setNoOfREceipts(int noOfREceipts) {
		this.noOfREceipts = noOfREceipts;
	}
	public Date getPeriodFrom() {
		return periodFrom;
	}
	public void setPeriodFrom(Date periodFrom) {
		this.periodFrom = periodFrom;
	}
	public Date getPeriodTo() {
		return periodTo;
	}
	public void setPeriodTo(Date periodTo) {
		this.periodTo = periodTo;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getTotalExpense() {
		return totalExpense;
	}
	public void setTotalExpense(Double totalExpense) {
		this.totalExpense = totalExpense;
	}
	public Double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public ClaimTypeMaster getClaimTypeId() {
		return claimTypeId;
	}

	public void setClaimTypeId(ClaimTypeMaster claimTypeId) {
		this.claimTypeId = claimTypeId;
	}

	
	public Boolean getClientBillable() {
		return clientBillable;
	}

	public void setClientBillable(Boolean clientBillable) {
		this.clientBillable = clientBillable;
	}

	public ClaimStatus getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(ClaimStatus finalStatus) {
		this.finalStatus = finalStatus;
	}


	
	public UserMaster getRequestingUserId() {
		return requestingUserId;
	}

	public void setRequestingUserId(UserMaster requestingUserId) {
		this.requestingUserId = requestingUserId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}

	public CurrencyMaster getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(CurrencyMaster currencyId) {
		this.currencyId = currencyId;
	}

	public Boolean getSpecialApprove() {
		return specialApprove;
	}

	public void setSpecialApprove(Boolean specialApprove) {
		this.specialApprove = specialApprove;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "ClaimSubmitHeader [id=" + id + ", claimNo=" + claimNo + ", claimDate=" + claimDate
				+ ", requestingUserId=" + requestingUserId + ", claimTypeId=" + claimTypeId + ", currencyId="
				+ currencyId + ", noOfREceipts=" + noOfREceipts + ", periodFrom=" + periodFrom + ", periodTo="
				+ periodTo + ", project=" + project + ", remarks=" + remarks + ", totalExpense=" + totalExpense
				+ ", claimAmount=" + claimAmount + ", clientBillable=" + clientBillable + ", financeType=" + financeType
				+ ", validatePersonId=" + validatePersonId + ", forwardPersonId=" + forwardPersonId
				+ ", secondLevelApproverId=" + secondLevelApproverId + ", approvedPersonId=" + approvedPersonId
				+ ", finalStatus=" + finalStatus + ", projectMasterId=" + projectMasterId + ", specialApprove="
				+ specialApprove + ", modifiedDate=" + modifiedDate + ", exchangeRate=" + exchangeRate + "]";
	}
	
	
	
}
