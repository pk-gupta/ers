package com.marlabs.rmbs.exception;

public class JwtTokenMissingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String s;

	public JwtTokenMissingException(String s){
		this.s=s;
	}

	@Override
	public String toString() {
		return "JwtTokenMissingException [s=" + s + "]";
	}
}
