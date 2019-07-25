package com.marlabs.rmbs.request.vo;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

public class ClaimRequestDetailsVo extends ResourceSupport {

	private Integer claimSubmitHeaderId;
	private Integer detailsId;
	private Date billDate;
	private String issuedTo;
	private Boolean verified;
	private Double companyPaid;
	private String remarks;
	private Integer billCurrency;
	private String sourceAddress;
	private String destinationAddress;
	private String claimCategory;
	private Double lessPaidByCompany;
	private String billNo;
	private Boolean clientBillable;
	private Double distance;
	public byte[] file;
	private String fileName;
	private String fileLink;
	private String vendor;
	private String particulars;
	private Date fromDate;
	private Date toDate;
	private Date journeyDate;
	private String visitedLocation;
	private String mode;
	private Double rate;
	private String unit;
	private Double payableAmount;
	private String financeRemarks;
	private Integer giftQuantity;
	private Double claimAmount;
	private Double expenseAmount;	
	
	
	public ClaimRequestDetailsVo() {
		super();
	}


	

	public ClaimRequestDetailsVo(Integer claimSubmitHeaderId, Integer detailsId, Date billDate, String issuedTo,
			Boolean verified, Double companyPaid, String remarks, Integer billCurrency, String sourceAddress,
			String destinationAddress, String claimCategory, Double lessPaidByCompany, String billNo,
			Boolean clientBillable, Double distance, byte[] file, String fileName, String fileLink, String vendor,
			String particulars, Date fromDate, Date toDate, Date journeyDate, String visitedLocation, String mode,
			Double rate, String unit, Double payableAmount, String financeRemarks, Integer giftQuantity,
			Double claimAmount, Double expenseAmount) {
		super();
		this.claimSubmitHeaderId = claimSubmitHeaderId;
		this.detailsId = detailsId;
		this.billDate = billDate;
		this.issuedTo = issuedTo;
		this.verified = verified;
		this.companyPaid = companyPaid;
		this.remarks = remarks;
		this.billCurrency = billCurrency;
		this.sourceAddress = sourceAddress;
		this.destinationAddress = destinationAddress;
		this.claimCategory = claimCategory;
		this.lessPaidByCompany = lessPaidByCompany;
		this.billNo = billNo;
		this.clientBillable = clientBillable;
		this.distance = distance;
		this.file = file;
		this.fileName = fileName;
		this.fileLink = fileLink;
		this.vendor = vendor;
		this.particulars = particulars;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.journeyDate = journeyDate;
		this.visitedLocation = visitedLocation;
		this.mode = mode;
		this.rate = rate;
		this.unit = unit;
		this.payableAmount = payableAmount;
		this.financeRemarks = financeRemarks;
		this.giftQuantity = giftQuantity;
		this.claimAmount = claimAmount;
		this.expenseAmount = expenseAmount;
	}




	public Integer getClaimSubmitHeaderId() {
		return claimSubmitHeaderId;
	}


	public void setClaimSubmitHeaderId(Integer claimSubmitHeaderId) {
		this.claimSubmitHeaderId = claimSubmitHeaderId;
	}


	public Integer getDetailsId() {
		return detailsId;
	}


	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}


	public Date getBillDate() {
		return billDate;
	}


	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}


	public String getIssuedTo() {
		return issuedTo;
	}


	public void setIssuedTo(String issuedTo) {
		this.issuedTo = issuedTo;
	}


	public Boolean getVerified() {
		return verified;
	}


	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

    
	public String getParticulars() {
		return particulars;
	}


	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}


	public Double getCompanyPaid() {
		return companyPaid;
	}


	public void setCompanyPaid(Double companyPaid) {
		this.companyPaid = companyPaid;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Integer getBillCurrency() {
		return billCurrency;
	}


	public void setBillCurrency(Integer billCurrency) {
		this.billCurrency = billCurrency;
	}


	public String getSourceAddress() {
		return sourceAddress;
	}


	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}


	public String getDestinationAddress() {
		return destinationAddress;
	}


	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}


	public String getClaimCategory() {
		return claimCategory;
	}


	public void setClaimCategory(String claimCategory) {
		this.claimCategory = claimCategory;
	}


	public Double getLessPaidByCompany() {
		return lessPaidByCompany;
	}


	public void setLessPaidByCompany(Double lessPaidByCompany) {
		this.lessPaidByCompany = lessPaidByCompany;
	}


	public String getBillNo() {
		return billNo;
	}


	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


	public Boolean getClientBillable() {
		return clientBillable;
	}


	public void setClientBillable(Boolean clientBillable) {
		this.clientBillable = clientBillable;
	}


	public Double getDistance() {
		return distance;
	}


	public void setDistance(Double distance) {
		this.distance = distance;
	}


	public byte[] getFile() {
		return file;
	}


	public void setFile(byte[] file) {
		this.file = file;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileLink() {
		return fileLink;
	}


	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}


	public String getVendor() {
		return vendor;
	}


	public void setVendor(String vendor) {
		this.vendor = vendor;
	}


	public Date getFromDate() {
		return fromDate;
	}


	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	public Date getToDate() {
		return toDate;
	}


	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public Date getJourneyDate() {
		return journeyDate;
	}


	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}


	public String getVisitedLocation() {
		return visitedLocation;
	}


	public void setVisitedLocation(String visitedLocation) {
		this.visitedLocation = visitedLocation;
	}


	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}


	public Double getRate() {
		return rate;
	}


	public void setRate(Double rate) {
		this.rate = rate;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public Double getPayableAmount() {
		return payableAmount;
	}


	public void setPayableAmount(Double payableAmount) {
		this.payableAmount = payableAmount;
	}


	public String getFinanceRemarks() {
		return financeRemarks;
	}


	public void setFinanceRemarks(String financeRemarks) {
		this.financeRemarks = financeRemarks;
	}


	public Integer getGiftQuantity() {
		return giftQuantity;
	}


	public void setGiftQuantity(Integer giftQuantity) {
		this.giftQuantity = giftQuantity;
	}


	public Double getClaimAmount() {
		return claimAmount;
	}


	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}


	public Double getExpenseAmount() {
		return expenseAmount;
	}


	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	
	
	

	

}
