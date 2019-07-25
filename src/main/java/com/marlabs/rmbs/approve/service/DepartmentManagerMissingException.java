package com.marlabs.rmbs.approve.service;

public class DepartmentManagerMissingException extends Exception {
	private static final long serialVersionUID = 1L;
	private String s;

	public DepartmentManagerMissingException(String s){
		this.s=s;
	}

	@Override
	public String toString() {
		return "DepartmentManagerMissingException [s=" + s + "]";
	}
}