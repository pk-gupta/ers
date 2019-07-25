package com.marlabs.rmbs.request.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.marlabs.rmbs.entities.ClaimTypeMaster;
import com.marlabs.rmbs.entities.CurrencyMaster;
import com.marlabs.rmbs.user.vo.CommonBean;
import com.marlabs.rmbs.user.vo.UserDetails;

public class ClaimRequestHeaderVo {

	private Integer id;
	private String claimNo;
	private String claimDate;
	private Integer employeeId;
	private String empId;
	private String employeeName;
	private Integer claimType;
	private String claimTypeName;
	private Integer claimCurrency;
	private Integer approvedCurrencyId;
	private String currencyCode;
	private String emailToForward;
	private Date periodFrom;
	private Date periodTo;
	private Double totalExpense;
	private Double companyPaid;
	private Double claimedAmount;
	private Integer noOfReceipts;
	private Boolean clientBillable;
	private String department;
	private String project;
	private Integer projectId;
	private String remarks;
	private Double perDiemAmnt;
	private Integer managerId;
	private String managerName;
	private String status;
	private String submitType;
	private Integer claimsStatus;
	private Boolean specialApprove;
	private Double approvelLimit;
	private Boolean showApproval;
	private String modifiedDate;
	private double exchangeRate;
	private Integer designationId;
	private Integer designationLevel;
	private String designation;
	private String approveType;
	private Double approvedAmount;
	private String employeeProjectId;
	private Map<Integer, Double> totalAmount = new HashMap<>();
	private List<CurrencyMaster> currencyList = new ArrayList<>();
	private List<ClaimTypeMaster> claimTypeList = new ArrayList<>();
	private List<CommonBean> projectTypeList = new ArrayList<>();
	private List<UserDetails> approverList = new ArrayList<>();

	public ClaimRequestHeaderVo() {

	}

	public ClaimRequestHeaderVo(Integer id) {
		super();
		this.id = id;
	}

	public ClaimRequestHeaderVo(Integer id, String claimNo, String claimDate, Integer employeeId, String employeeName,
			Integer claimType, String claimTypeName, Integer claimCurrency, Date periodFrom, Date periodTo,
			Double totalExpense, Double companyPaid, Double claimedAmount, Integer noOfReceipts, Boolean clientBillable,
			String project, String remarks) {
		super();
		this.id = id;
		this.claimNo = claimNo;
		this.claimDate = claimDate;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.claimType = claimType;
		this.claimTypeName = claimTypeName;
		this.claimCurrency = claimCurrency;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.totalExpense = totalExpense;
		this.companyPaid = companyPaid;
		this.claimedAmount = claimedAmount;
		this.noOfReceipts = noOfReceipts;
		this.setClientBillable(clientBillable);
		this.project = project;
		this.remarks = remarks;
	}
    
	
	public Map<Integer, Double> getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Map<Integer, Double> totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}

	public Integer getDesignationLevel() {
		return designationLevel;
	}

	public void setDesignationLevel(Integer designationLevel) {
		this.designationLevel = designationLevel;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setShowClear(Boolean showClear) {

	}

	public Integer getApprovedCurrencyId() {
		return approvedCurrencyId;
	}

	public void setApprovedCurrencyId(Integer approvedCurrencyId) {
		this.approvedCurrencyId = approvedCurrencyId;
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

	public String getEmployeeProjectId() {
		return employeeProjectId;
	}

	public void setEmployeeProjectId(String employeeProjectId) {
		this.employeeProjectId = employeeProjectId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmailToForward() {
		return emailToForward;
	}

	public void setEmailToForward(String emailToForward) {
		this.emailToForward = emailToForward;
	}

	public double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getClaimType() {
		return claimType;
	}

	public void setClaimType(Integer claimType) {
		this.claimType = claimType;
	}

	public Integer getClaimCurrency() {
		return claimCurrency;
	}

	public void setClaimCurrency(Integer claimCurrency) {
		this.claimCurrency = claimCurrency;
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

	public Double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(Double totalExpense) {
		this.totalExpense = totalExpense;
	}

	public Double getCompanyPaid() {
		return companyPaid;
	}

	public void setCompanyPaid(Double companyPaid) {
		this.companyPaid = companyPaid;
	}

	public Double getClaimedAmount() {
		return claimedAmount;
	}

	public void setClaimedAmount(Double claimedAmount) {
		this.claimedAmount = claimedAmount;
	}

	public Integer getNoOfReceipts() {
		return noOfReceipts;
	}

	public void setNoOfReceipts(Integer noOfReceipts) {
		this.noOfReceipts = noOfReceipts;
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

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getClaimTypeName() {
		return claimTypeName;
	}

	public void setClaimTypeName(String claimTypeName) {
		this.claimTypeName = claimTypeName;
	}

	public List<CurrencyMaster> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<CurrencyMaster> currencyList) {
		this.currencyList = currencyList;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public List<ClaimTypeMaster> getClaimTypeList() {
		return claimTypeList;
	}

	public void setClaimTypeList(List<ClaimTypeMaster> claimTypeList) {
		this.claimTypeList = claimTypeList;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public Integer getClaimsStatus() {
		return claimsStatus;
	}

	public void setClaimsStatus(Integer claimsStatus) {
		this.claimsStatus = claimsStatus;
	}

	public Boolean getClientBillable() {
		return clientBillable;
	}

	public void setClientBillable(Boolean clientBillable) {
		this.clientBillable = clientBillable;
	}

	public List<UserDetails> getApproverList() {
		return approverList;
	}

	public void setApproverList(List<UserDetails> approverList) {
		this.approverList = approverList;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public List<CommonBean> getProjectTypeList() {
		return projectTypeList;
	}

	public void setProjectTypeList(List<CommonBean> projectTypeList) {
		this.projectTypeList = projectTypeList;
	}

	public Boolean getSpecialApprove() {
		return specialApprove;
	}

	public void setSpecialApprove(Boolean specialApprove) {
		this.specialApprove = specialApprove;
	}

	public Double getApprovelLimit() {
		return approvelLimit;
	}

	public void setApprovelLimit(Double approvelLimit) {
		this.approvelLimit = approvelLimit;
	}

	public Boolean getShowApproval() {
		return showApproval;
	}

	public void setShowApproval(Boolean showApproval) {
		this.showApproval = showApproval;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Double getPerDiemAmnt() {
		return perDiemAmnt;
	}

	public void setPerDiemAmnt(Double perDiemAmnt) {
		this.perDiemAmnt = perDiemAmnt;

	}

	@Override
	public String toString() {
		return "ClaimRequestHeaderVo [id=" + id + ", claimNo=" + claimNo + ", claimDate=" + claimDate + ", employeeId="
				+ employeeId + ", empId=" + empId + ", employeeName=" + employeeName + ", claimType=" + claimType
				+ ", claimTypeName=" + claimTypeName + ", claimCurrency=" + claimCurrency + ", approvedCurrencyId="
				+ approvedCurrencyId + ", currencyCode=" + currencyCode + ", emailToForward=" + emailToForward
				+ ", periodFrom=" + periodFrom + ", periodTo=" + periodTo + ", totalExpense=" + totalExpense
				+ ", companyPaid=" + companyPaid + ", claimedAmount=" + claimedAmount + ", noOfReceipts=" + noOfReceipts
				+ ", clientBillable=" + clientBillable + ", department=" + department + ", project=" + project
				+ ", projectId=" + projectId + ", remarks=" + remarks + ", perDiemAmnt=" + perDiemAmnt + ", managerId="
				+ managerId + ", managerName=" + managerName + ", status=" + status + ", submitType=" + submitType
				+ ", claimsStatus=" + claimsStatus + ", specialApprove=" + specialApprove + ", approvelLimit="
				+ approvelLimit + ", showApproval=" + showApproval + ", modifiedDate=" + modifiedDate
				+ ", exchangeRate=" + exchangeRate + ", DesignationId=" + designationId + ", DesignationLevel="
				+ designationLevel + ", Designation=" + designation + ", approveType=" + approveType
				+ ", approvedAmount=" + approvedAmount + ", employeeProjectId=" + employeeProjectId + ", currencyList="
				+ currencyList + ", claimTypeList=" + claimTypeList + ", projectTypeList=" + projectTypeList
				+ ", approverList=" + approverList + "]";
	}

}
