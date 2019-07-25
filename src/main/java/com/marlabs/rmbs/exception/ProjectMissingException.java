package com.marlabs.rmbs.exception;

public class ProjectMissingException extends Exception {
	private static final long serialVersionUID = 1L;
	private String s;

	public ProjectMissingException(String s){
		this.s=s;
	}

	@Override
	public String toString() {
		return "ProjectMissingException [s=" + s + "]";
	}
}