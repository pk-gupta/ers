package com.marlabs.rmbs.masters.constant;


public enum RoleConstant {

	ADMIN(1, "ADMIN"), APPROVER(2, "APPROVER"),
	NORMAL_USER(3, "NORMAL_USER"), SUPER_ADMIN(4, "SUPER_ADMIN"), MANAGER(5,"MANAGER");

	public Integer value;
	public String name;

	private RoleConstant(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public boolean equalsRoles(String otherName) {
		return (otherName == null) ? false : name.equalsIgnoreCase(otherName);
	}
}