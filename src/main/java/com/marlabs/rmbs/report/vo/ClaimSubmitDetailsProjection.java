package com.marlabs.rmbs.report.vo;

public class ClaimSubmitDetailsProjection {
	private String claimCategory;
	private Integer billCurrency;
	private Double claimAmount;
	
	
	public ClaimSubmitDetailsProjection(String claimCategory, Integer billCurrency, Double claimAmount) {
		super();
		this.claimCategory = claimCategory;
		this.billCurrency = billCurrency;
		this.claimAmount = claimAmount;
	}


	public String getClaimCategory() {
		return claimCategory;
	}


	public void setClaimCategory(String claimCategory) {
		this.claimCategory = claimCategory;
	}


	public Integer getBillCurrency() {
		return billCurrency;
	}


	public void setBillCurrency(Integer billCurrency) {
		this.billCurrency = billCurrency;
	}


	public Double getClaimAmount() {
		return claimAmount;
	}


	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}
	
	
}
