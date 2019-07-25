package com.marlabs.rmbs.user.vo;

public class EmployeeProjectVo {
	private Integer id;
	public EmployeeProjectVo(Integer id, String employeeProjectId, String employeeProject) {
		this.id = id;
		this.employeeProjectId = employeeProjectId;
		this.employeeProject = employeeProject;
		
	}
	private String employeeProject;
	private String employeeProjectId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmployeeProject() {
		return employeeProject;
	}
	public void setEmployeeProject(String employeeProject) {
		this.employeeProject = employeeProject;
	}
	public String getEmployeeProjectId() {
		return employeeProjectId;
	}
	public void setEmployeeProjectId(String employeeProjectId) {
		this.employeeProjectId = employeeProjectId;
	}
}
