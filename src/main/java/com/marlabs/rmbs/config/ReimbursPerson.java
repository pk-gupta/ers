package com.marlabs.rmbs.config;

import com.marlabs.rmbs.entities.DepartmentMaster;
import com.marlabs.rmbs.entities.RoleMaster;

public class ReimbursPerson{
	
	private Integer id;
	private String uid;
	private String cn;
	private String mail;
	private String givenName;
	private String telephoneNumber;
	private String department;
	private String loc;
	private String manager;
	private String userPrincipalName;
	private String employeeId;
	private String employeeManagerId;
	private Integer userManagerId;
	private Integer employeeDepartmentId;
	
	
	
	public Integer getEmployeeDepartmentId() {
		return employeeDepartmentId;
	}
	public void setEmployeeDepartmentId(Integer employeeDepartmentId) {
		this.employeeDepartmentId = employeeDepartmentId;
	}
	public void setUserManagerId(Integer userManagerId) {
		this.userManagerId = userManagerId;
	}
	public Integer getUserManagerId() {
		return userManagerId;
	}
	private Boolean isManager;
	private Boolean isBillable;
	private Boolean isActive;
	private RoleMaster roleId;
	private DepartmentMaster departmentId;
	
	
    public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String Designation) {
		this.Designation = Designation;
	}
	public Integer getDesignationLevel() {
		return DesignationLevel;
	}
	public void setDesignationLevel(Integer DesignationLevel) {
		this.DesignationLevel = DesignationLevel;
	}
	public void setDesignationId(Integer DesignationId) {
		this.DesignationId = DesignationId;
	}
	private String Designation;
    private Integer DesignationId;
    private Integer DesignationLevel;


	
	
public RoleMaster getRoleId() {
		return roleId;
	}

	public void setRoleId(RoleMaster roleId) {
		this.roleId = roleId;
	}

public Boolean getIsManager() {
		return isManager;
	}


	public int getDesignationId() {
	return DesignationId;
}

public void setDesignationId(int DesignationId) {
	this.DesignationId = DesignationId;
}


	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	


	public DepartmentMaster getDepartmentId() {
	return departmentId;
}

public void setDepartmentId(DepartmentMaster departmentId) {
	this.departmentId = departmentId;
}


	public String getEmployeeManagerId() {
		return employeeManagerId;
	}

	public void setEmployeeManagerId(String employeeManagerId) {
		this.employeeManagerId = employeeManagerId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public ReimbursPerson(){
		
	}

	public ReimbursPerson(Integer id,String uid,String cn,String mail,
			String givenName,String telephoneNumber,String department,String loc,String manager,String userPrincipalName,String employeeId){
		this.id=id;
		this.uid=uid;
		this.cn=cn;
		this.mail=mail;
		this.givenName=givenName;
		this.telephoneNumber=telephoneNumber;
		this.department=department;
		this.loc=loc;
		this.manager=manager;
		this.userPrincipalName=userPrincipalName;
		this.employeeId=employeeId;
		
		
	}
	
	public ReimbursPerson(String uid,String cn,String mail,
			String givenName,String telephoneNumber,String department,String loc,String manager,String userPrincipalName){
		this.uid=uid;
		this.cn=cn;
		this.mail=mail;
		this.givenName=givenName;
		this.telephoneNumber=telephoneNumber;
		this.department=department;
		this.loc=loc;
		this.manager=manager;
		this.userPrincipalName=userPrincipalName;
	}

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getUserPrincipalName() {
		return userPrincipalName;
	}
	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursPerson other = (ReimbursPerson) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	public Boolean getIsBillable() {
		return isBillable;
	}
	public void setIsBillable(Boolean isBillable) {
		this.isBillable = isBillable;
	}
	
	
	

	
}
