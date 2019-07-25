package com.marlabs.rmbs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_master")
public class UserMaster {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="employee_id")
	private String employeeId;
	@Column(name="employee_name")
	private String employeeName;
	@Column(name="cn")
	private String cn;
	@Column(name="mail")
	private String mail;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="location")
	private String location;
	@Column(name="employee_manager_id")
	private String employeeManagerId;
	@Column(name="user_manager_id")
	private Integer userManagerId;
	@Column(name="employee_manager")
	private String employeeManager;
	@Column(name="manager")
	private String manager;
	
	@Column(name="employee_department_id")
	private Integer employeeDepartmentId;
	@Column(name="employee_department")
	private String employeeDepartment;
	@Column(name="department_id")
	private Integer departmentId;
	@Column(name="department")
	private String department;
	
	
	
	@Column(name="designation")
	private String designation;
	@Column(name="designation_id")
	private Integer designationId;
	@Column(name="designation_level")
	private Integer designationLevel;
	@Column(name="is_manager")
	private Boolean isManager;
	@Column(name="is_billable")
	private Boolean isBillable;
	@Column(name="is_active")
	private Boolean isActive;
	@Column(name="employee_type",length=5)
	private String employeeType;
	@Column(name="role_id")
	private Integer roleId;
	
	
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmployeeManagerId() {
		return employeeManagerId;
	}
	public void setEmployeeManagerId(String employeeManagerId) {
		this.employeeManagerId = employeeManagerId;
	}
	public Integer getUserManagerId() {
		return userManagerId;
	}
	public void setUserManagerId(Integer userManagerId) {
		this.userManagerId = userManagerId;
	}
	public String getEmployeeManager() {
		return employeeManager;
	}
	public void setEmployeeManager(String employeeManager) {
		this.employeeManager = employeeManager;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public Integer getEmployeeDepartmentId() {
		return employeeDepartmentId;
	}
	public void setEmployeeDepartmentId(Integer employeeDepartmentId) {
		this.employeeDepartmentId = employeeDepartmentId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmployeeDepartment() {
		return employeeDepartment;
	}
	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	public Boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}
	public Boolean getIsBillable() {
		return isBillable;
	}
	public void setIsBillable(Boolean isBillable) {
		this.isBillable = isBillable;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "UserMaster [id=" + id + ", employeeId=" + employeeId + ", employeeName=" + employeeName + ", cn=" + cn
				+ ", mail=" + mail + ", phoneNumber=" + phoneNumber + ", location=" + location + ", employeeManagerId="
				+ employeeManagerId + ", userManagerId=" + userManagerId + ", employeeManager=" + employeeManager
				+ ", manager=" + manager + ", employeeDepartmentId=" + employeeDepartmentId + ", employeeDepartment="
				+ employeeDepartment + ", departmentId=" + departmentId + ", department=" + department
				+ ", designation=" + designation + ", designationId=" + designationId + ", designationLevel="
				+ designationLevel + ", isManager=" + isManager + ", isBillable=" + isBillable + ", isActive="
				+ isActive + ", employeeType=" + employeeType + ", roleId=" + roleId + "]";
	}
	
	
	
	

}
/*package com.marlabs.rmbs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_master")
public class UserMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String uid;
	private String cn;
	private String mail;
	private String telephoneNumber;
	private String department;
	private String loc;
	private String manager;
	private String employeeManagerId;
	private String submissionTo;
	private String approveFwdTo;
	private String validateTo;
	private String userPrincipalName;
	private String givenName;
	private Integer employeeDepartmentId;
	private String Designation;
	private Integer DesignationId;
	private Integer DesignationLevel;
	private Double approvalLimit = 0.0;
	private Boolean IsManager;
	private Boolean IsBillable;
	private String employeeId;
	private String employeeType;
	private String userCode;
	private Boolean isActive;

	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean specialApprove = false;

	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private RoleMaster roleId;

	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private UserMaster userManagerId;

	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Department departmentId;
	
	public UserMaster() {

	}
	
	public UserMaster(int id, String cn) {
		super();
		this.id = id;
		this.cn = cn;
	}

	public String getEmployeeManagerId() {
		return employeeManagerId;
	}

	public void setEmployeeManagerId(String employeeManagerId) {
		this.employeeManagerId = employeeManagerId;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getValidateTo() {
		return validateTo;
	}

	public void setValidateTo(String validateTo) {
		this.validateTo = validateTo;
	}

	public Integer getEmployeeDepartmentId() {
		return employeeDepartmentId;
	}

	public void setEmployeeDepartmentId(Integer employeeDepartmentId) {
		this.employeeDepartmentId = employeeDepartmentId;
	}
    
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

	public Integer getDesignationId() {
		return DesignationId;
	}

	public void setDesignationId(Integer DesignationId) {
		this.DesignationId = DesignationId;
	}

	public Integer getDesignationLevel() {
		return DesignationLevel;
	}

	public void setDesignationLevel(Integer DesignationLevel) {
		this.DesignationLevel = DesignationLevel;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RoleMaster getRoleId() {
		return roleId;
	}

	public void setRoleId(RoleMaster roleId) {
		this.roleId = roleId;
	}

	public UserMaster getUserManagerId() {
		return userManagerId;
	}

	public void setUserManagerId(UserMaster userManagerId) {
		this.userManagerId = userManagerId;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Department getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}

	public String getSubmissionTo() {
		return submissionTo;
	}

	public void setSubmissionTo(String submissionTo) {
		this.submissionTo = submissionTo;
	}

	public String getApproveFwdTo() {
		return approveFwdTo;
	}

	public void setApproveFwdTo(String approveFwdTo) {
		this.approveFwdTo = approveFwdTo;
	}

	public Boolean getSpecialApprove() {
		return specialApprove;
	}

	public void setSpecialApprove(Boolean specialApprove) {
		this.specialApprove = specialApprove;
	}

	public Double getApprovalLimit() {
		return approvalLimit;
	}

	public void setApprovalLimit(Double approvalLimit) {
		this.approvalLimit = approvalLimit;
	}

	public Boolean getIsManager() {
		return IsManager;
	}

	public void setIsManager(Boolean isManager) {
		IsManager = isManager;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Boolean getIsBillable() {
		return IsBillable;
	}

	public void setIsBillable(Boolean isBillable) {
		IsBillable = isBillable;
	}

	@Override
	public String toString() {
		return "UserMaster [id=" + id + ", uid=" + uid + ", cn=" + cn + ", mail=" + mail + ", telephoneNumber="
				+ telephoneNumber + ", department=" + department + ", loc=" + loc + ", manager=" + manager
				+ ", employeeManagerId=" + employeeManagerId + ", submissionTo=" + submissionTo + ", approveFwdTo="
				+ approveFwdTo + ", validateTo=" + validateTo + ", userPrincipalName=" + userPrincipalName
				+ ", givenName=" + givenName + ", employeeDepartmentId=" + employeeDepartmentId + ", Designation="
				+ Designation + ", DesignationId=" + DesignationId + ", DesignationLevel=" + DesignationLevel
				+ ", approvalLimit=" + approvalLimit + ", IsManager=" + IsManager + ", IsBillable=" + IsBillable
				+ ", employeeId=" + employeeId + ", employeeType=" + employeeType + ", userCode=" + userCode
				+ ", isActive=" + isActive + ", specialApprove=" + specialApprove + ", roleId=" + roleId
				+ ", userManagerId=" + userManagerId + ", departmentId=" + departmentId + "]";
	}
	

	

}
*/