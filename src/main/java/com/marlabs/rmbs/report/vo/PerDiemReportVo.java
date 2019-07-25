package com.marlabs.rmbs.report.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PerDiemReportVo {
	private String claimNo;
	private String employeeId;
	private String employeeName;
	private Date claimDate;
	private Date approvalDate;
	private String summary;
	private String claimCategory;
	private Double perDiemAmount;
	private Integer perDiemAmountCurrency;
	private Double taxableAmount;
	private Double nonTaxableAmount;
	private Map<Integer, Double> otherAmount = new HashMap<>();
	
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Date getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getClaimCategory() {
		return claimCategory;
	}
	public void setClaimCategory(String claimCategory) {
		this.claimCategory = claimCategory;
	}
	
	public Double getPerDiemAmount() {
		return perDiemAmount;
	}
	public void setPerDiemAmount(Double perDiemAmount) {
		this.perDiemAmount = perDiemAmount;
	}
	
	public Integer getPerDiemAmountCurrency() {
		return perDiemAmountCurrency;
	}
	public void setPerDiemAmountCurrency(Integer perDiemAmountCurrency) {
		this.perDiemAmountCurrency = perDiemAmountCurrency;
	}
	public Double getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	public Double getNonTaxableAmount() {
		return nonTaxableAmount;
	}
	public void setNonTaxableAmount(Double nonTaxableAmount) {
		this.nonTaxableAmount = nonTaxableAmount;
	}
	public Map<Integer, Double> getOtherAmount() {
		return otherAmount;
	}
	public void setOtherAmount(Map<Integer, Double> otherAmount) {
		this.otherAmount = otherAmount;
	}
	@Override
	public String toString() {
		return "PerDiemReportVo [claimNo=" + claimNo + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", claimDate=" + claimDate + ", approvalDate=" + approvalDate + ", summary=" + summary
				+ ", claimCategory=" + claimCategory + ", perDiemAmount=" + perDiemAmount + ", perDiemAmountCurrency="
				+ perDiemAmountCurrency + ", taxableAmount=" + taxableAmount + ", nonTaxableAmount=" + nonTaxableAmount
				+ ", otherAmount=" + otherAmount + "]";
	}
	
	
	
	
    
	
}
