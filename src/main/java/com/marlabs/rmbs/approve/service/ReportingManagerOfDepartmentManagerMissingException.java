package com.marlabs.rmbs.approve.service;

public class ReportingManagerOfDepartmentManagerMissingException extends Exception {
	private static final long serialVersionUID = 1L;
	private String s;

	public ReportingManagerOfDepartmentManagerMissingException(String s){
		this.s=s;
	}

	@Override
	public String toString() {
		return "DepartmentManagerMissingException [s=" + s + "]";
	}
}