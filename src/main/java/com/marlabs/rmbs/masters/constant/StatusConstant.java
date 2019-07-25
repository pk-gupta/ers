package com.marlabs.rmbs.masters.constant;



public enum StatusConstant { 
	DRAFT(1), PENDING(2), APPROVE(3), REVIEW(4), FORWARD(5), HOLD(6),CLEAR(7),REJECT(8),VALIDATE(10);
	
	public Integer value;
	private StatusConstant(int value) { 
		this.value = value;
	} 
}

