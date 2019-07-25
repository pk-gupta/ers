package com.marlabs.rmbs.login.vo;
public class LoginVo {
	private Integer id;
	private String employeeId;
	private String employeeName;
	private String designation;
	private Integer role;
	private String employeeManager;
	private String mail;
	private String location;
	private String employeeDepartment;
	private String employeeType;
	private Boolean approver;
	private Boolean clearer;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public String getEmployeeManager() {
		return employeeManager;
	}
	public void setEmployeeManager(String employeeManager) {
		this.employeeManager = employeeManager;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmployeeDepartment() {
		return employeeDepartment;
	}
	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	
	public Boolean getApprover() {
		return approver;
	}
	public void setApprover(Boolean approver) {
		this.approver = approver;
	}
	public Boolean getClearer() {
		return clearer;
	}
	public void setClearer(Boolean clearer) {
		this.clearer = clearer;
	}
	@Override
	public String toString() {
		return "LoginVo [id=" + id + ", employeeId=" + employeeId + ", employeeName=" + employeeName + ", designation="
				+ designation + ", role=" + role + ", employeeManager=" + employeeManager + ", mail=" + mail
				+ ", location=" + location + ", employeeDepartment=" + employeeDepartment + ", employeeType="
				+ employeeType + ", approver=" + approver + ", clearer=" + clearer + "]";
	}
	
	
	
	
	

}
