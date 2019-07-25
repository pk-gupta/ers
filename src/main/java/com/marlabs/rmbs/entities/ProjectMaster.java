package com.marlabs.rmbs.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "project_master")
public class ProjectMaster {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "project_id")
	private Integer projectId;
	@Column(name = "project_code")
	private String projectCode;
	@Column(name = "project_desc")
	private String projectDesc;
	@Column(name = "project_manager_id")
	private String projectManagerId;
	@Column(name = "project_manager")
	private String projectManager;
	@Column(name="is_active")
	private Boolean isActive;
	@Column(name="is_billable")
	private Boolean isBillable;
	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;
	@Temporal(TemporalType.DATE)
	@Column(name="closure_date")
	private Date closureDate;
	@Column(name="project_type")
	private String projectType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public String getProjectManagerId() {
		return projectManagerId;
	}
	public void setProjectManagerId(String projectManagerId) {
		this.projectManagerId = projectManagerId;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public Boolean getIsBillable() {
		return isBillable;
	}
	public void setIsBillable(Boolean isBillable) {
		this.isBillable = isBillable;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getClosureDate() {
		return closureDate;
	}
	public void setClosureDate(Date closureDate) {
		this.closureDate = closureDate;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	@Override
	public String toString() {
		return "ProjectMaster [id=" + id + ", projectId=" + projectId + ", projectCode=" + projectCode
				+ ", projectDesc=" + projectDesc + ", projectManagerId=" + projectManagerId + ", projectManager="
				+ projectManager + ", isActive=" + isActive + ", isBillable=" + isBillable + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", closureDate=" + closureDate + ", projectType=" + projectType + "]";
	}

	
}
