package com.marlabs.rmbs.request.vo;

import java.util.ArrayList;
import java.util.List;

import com.marlabs.rmbs.entities.ClaimTypeMaster;
import com.marlabs.rmbs.entities.CurrencyMaster;
import com.marlabs.rmbs.user.vo.EmployeeProjectVo;

public class ClaimRequestLoad {
	
	
	
	private String claimDate;
	private String reportTo;
	private Double perDiemAmnt;
	private Double perDiemAmntInternational;
	private Double perDiemAmntOwn;
	private Double perDiemAmntInternationalOwn;
	/*private String claimNo;
    private Integer managerId;
	private String managerName;
    private Integer employeeId;
	private String employeeName;
	private Integer roleId;
	private String roleName;
	private List<CommonBean> projectTypeList=new ArrayList<>();*/
	
	public Double getPerDiemAmntInternational() {
		return perDiemAmntInternational;
	}
	public void setPerDiemAmntInternational(Double perDiemAmntInternational) {
		this.perDiemAmntInternational = perDiemAmntInternational;
	}
	private List<CurrencyMaster> currencyList=new ArrayList<>();
	private List<ClaimTypeMaster> claimTypeList=new ArrayList<>();
	private List<EmployeeProjectVo> projectList=new ArrayList<>();
	
	private List<String> locationList=new ArrayList<>();
	
	
	//Getters And Setters
	public List<EmployeeProjectVo> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<EmployeeProjectVo> projectList) {
		this.projectList = projectList;
	}
    
	
	public Double getPerDiemAmnt() {
		return perDiemAmnt;
	}
	public void setPerDiemAmnt(Double perDiemAmnt) {
		this.perDiemAmnt = perDiemAmnt;
	}
	
	public List<CurrencyMaster> getCurrencyList() {
		return currencyList;
	}
	public void setCurrencyList(List<CurrencyMaster> currencyList) {
		this.currencyList = currencyList;
	}
	public List<ClaimTypeMaster> getClaimTypeList() {
		return claimTypeList;
	}
	public void setClaimTypeList(List<ClaimTypeMaster> claimTypeList) {
		this.claimTypeList = claimTypeList;
	}
	public String getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}
	public String getReportTo() {
		return reportTo;
	}
	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}
	public Double getPerDiemAmntOwn() {
		return perDiemAmntOwn;
	}
	public void setPerDiemAmntOwn(Double perDiemAmntOwn) {
		this.perDiemAmntOwn = perDiemAmntOwn;
	}
	public Double getPerDiemAmntInternationalOwn() {
		return perDiemAmntInternationalOwn;
	}
	public void setPerDiemAmntInternationalOwn(Double perDiemAmntInternationalOwn) {
		this.perDiemAmntInternationalOwn = perDiemAmntInternationalOwn;
	}
	public List<String> getLocationList() {
		return locationList;
	}
	public void setLocationList(List<String> locationList) {
		this.locationList = locationList;
	}
	
	
	
	
	
	
	

	/*public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}public List<CommonBean> getProjectTypeList() {
		return projectTypeList;
	}
	public void setProjectTypeList(List<CommonBean> projectTypeList) {
		this.projectTypeList = projectTypeList;
	}*/
	
	
	
	
	
}
