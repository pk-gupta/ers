package com.marlabs.rmbs.exception;

public class FileNotSupportedException extends Exception {
	private static final long serialVersionUID = 1L;
	private final String s;

	public FileNotSupportedException(String s){
		this.s=s;
	}

	@Override
	public String toString() {
		return "FileNotSupportedException [s=" + s + "]";
	}
}