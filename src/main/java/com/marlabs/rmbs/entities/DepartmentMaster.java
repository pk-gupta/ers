package com.marlabs.rmbs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department_master")
public class DepartmentMaster {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="depatment_id")
	private Integer depatmentId;
	@Column(name="department_manager_id")
	private String departmentManagerId;
	@Column(name="department_manager")
	private String departmentManager;
	@Column(name="department_code")
	private String departmentCode;
	@Column(name="department_desc")
	private String departmentDesc;

	
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentManagerId() {
		return departmentManagerId;
	}

	public void setDepartmentManagerId(String departmentManagerId) {
		this.departmentManagerId = departmentManagerId;
	}

	public String getDepartmentManager() {
		return departmentManager;
	}

	public void setDepartmentManager(String departmentManager) {
		this.departmentManager = departmentManager;
	}

	public Integer getDepatmentId() {
		return depatmentId;
	}

	public void setDepatmentId(Integer depatmentId) {
		this.depatmentId = depatmentId;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentDesc() {
		return departmentDesc;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", depatmentId=" + depatmentId + ", departmentManagerId=" + departmentManagerId
				+ ", departmentManager=" + departmentManager + ", departmentCode=" + departmentCode
				+ ", departmentDesc=" + departmentDesc + "]";
	}
	
	


}
