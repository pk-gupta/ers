package com.marlabs.rmbs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_project")
public class EmployeeProject {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "project_id")
	private Integer projectId;
    
	@Column(name = "employee_project")
	private String employeeProject;
	
	@Column(name = "employee_project_id")
    private String employeeProjectCode;
    
    @Column(name="employee_name")
	private String employeeName;
    
    @Column(name="location")
	private String location;
	
    @Column(name="manager")
	private String manager;
    
    @Column(name="manager_id")
	private String managerId;

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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getEmployeeProject() {
		return employeeProject;
	}

	public void setEmployeeProject(String employeeProject) {
		this.employeeProject = employeeProject;
	}

	public String getEmployeeProjectCode() {
		return employeeProjectCode;
	}

	public void setEmployeeProjectCode(String employeeProjectCode) {
		this.employeeProjectCode = employeeProjectCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

    
}