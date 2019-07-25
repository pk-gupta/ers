package com.marlabs.rmbs.user.vo;

import java.util.ArrayList;
import java.util.List;

import com.marlabs.rmbs.entities.DepartmentMaster;
import com.marlabs.rmbs.entities.RoleMaster;

public class AdminRequest {
	
	
	private Integer userId;
	private Integer department;
	private Integer roleId;
	private Integer submitfinanceId;
	private String submissionTo;
	private Double approveLimit;
	private String realEmp;
	
	
	public String getRealEmp() {
		return realEmp;
	}
	public void setRealEmp(String realEmp) {
		this.realEmp = realEmp;
	}
	private String approveFwdTo;
	private Integer approveFwdFinanceId;
	
	private List<DepartmentMaster> departmentList=new ArrayList<>();
	private List<RoleMaster> roleList=new ArrayList<>();
	private List<CommonBean> financePersonList=new ArrayList<>();
	
	 
	private List<CommonBean> submitToEmployees=new ArrayList<>();
	private List<CommonBean> approveToEmployees=new ArrayList<>();
	

	
	
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDepartment() {
		return department;
	}
	public void setDepartment(Integer department) {
		this.department = department;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<DepartmentMaster> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<DepartmentMaster> departmentList) {
		this.departmentList = departmentList;
	}
	public List<RoleMaster> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleMaster> roleList) {
		this.roleList = roleList;
	}

	public String getSubmissionTo() {
		return submissionTo;
	}
	public void setSubmissionTo(String submissionTo) {
		this.submissionTo = submissionTo;
	}
	public Integer getSubmitfinanceId() {
		return submitfinanceId;
	}
	public void setSubmitfinanceId(Integer submitfinanceId) {
		this.submitfinanceId = submitfinanceId;
	}
	public String getApproveFwdTo() {
		return approveFwdTo;
	}
	public void setApproveFwdTo(String approveFwdTo) {
		this.approveFwdTo = approveFwdTo;
	}
	public Integer getApproveFwdFinanceId() {
		return approveFwdFinanceId;
	}
	public void setApproveFwdFinanceId(Integer approveFwdFinanceId) {
		this.approveFwdFinanceId = approveFwdFinanceId;
	}
	public List<CommonBean> getFinancePersonList() {
		return financePersonList;
	}
	public void setFinancePersonList(List<CommonBean> financePersonList) {
		this.financePersonList = financePersonList;
	}
	public List<CommonBean> getSubmitToEmployees() {
		return submitToEmployees;
	}
	public void setSubmitToEmployees(List<CommonBean> submitToEmployees) {
		this.submitToEmployees = submitToEmployees;
	}
	public List<CommonBean> getApproveToEmployees() {
		return approveToEmployees;
	}
	public void setApproveToEmployees(List<CommonBean> approveToEmployees) {
		this.approveToEmployees = approveToEmployees;
	}
	public Double getApproveLimit() {
		return approveLimit;
	}
	public void setApproveLimit(Double approveLimit) {
		this.approveLimit = approveLimit;
	}
	
	
	
	
	
	
	

}
