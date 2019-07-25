package com.marlabs.rmbs.request.vo;

public class PerDiem {
	
	private int currency;
	private Double totalPerDiemAmnt;
	public Double getTotalPerDiemAmnt() {
		return totalPerDiemAmnt;
	}
	public void setTotalPerDiemAmnt(Double totalPerDiemAmnt) {
		this.totalPerDiemAmnt = totalPerDiemAmnt;
	}
	public int getCurrency() {
		return currency;
	}
	
	
	public void setCurrency(int currency) {
		this.currency = currency;
		
	}
}
