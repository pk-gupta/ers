package com.marlabs.rmbs.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "claimsubmit_details")
public class ClaimSubmitDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne  
	@JoinColumn(name ="claim_header_id")  
	private ClaimSubmitHeader claimHeaderId; 
	private String issuedTo;
	private Date billDate;
	private String particulars;
	private String billNo;
	private Boolean clientBillable;	
	private Integer giftQuantity;
	private Boolean verified;
	private Double payableAmount;
	private String financeRemarks;
	
	
	
	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
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

	public Boolean getClientBillable() {
		return clientBillable;
	}

	public void setClientBillable(Boolean clientBillable) {
		this.clientBillable = clientBillable;
	}

	private Integer billCurrency;
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

	private String sourceAddress;
	private String destinationAddress;
	private Double totalExpenseAmount;
	private Double claimAmount;
	@Column(name="remarks",length=500)
	private String remarks;
	private String vendor;
	private String claimCategory;
	private Double lessPaidByCompany;	
	
	private Date journeyDate;
	private String visitedLocation;
	private String mode;
	private Double rate;
	private String unit;
	private Double distance;
	private byte[] file;
	private String fileName;
	//from and to date fields
	private Date fromDate;
	private Date toDate;
	
	public ClaimSubmitDetails(){}
	
	public ClaimSubmitDetails(ClaimSubmitHeader claimHeaderId, Date billDate, String particulars, String billNo,
			String vendor,Double totalExpenseAmount, Double claimAmount, String remarks) {
		this.claimHeaderId = claimHeaderId;
		this.billDate = billDate;
		this.particulars = particulars;
		this.billNo = billNo;
		this.totalExpenseAmount = totalExpenseAmount;
		this.claimAmount = claimAmount;
		this.remarks = remarks;
		this.vendor=vendor;
	}
	
	
	// Getting bill details object without file
	public ClaimSubmitDetails(int id, String claimCategory,String mode,  Date billDate, String visitedLocation,
			String sourceAddress,String destinationAddress,String vendor,Integer billCurrency,
			Double rate,Double distance,String unit,Integer giftQuantity,String issuedTo,
			Date fromDate, Date toDate,Double totalExpenseAmount, Double claimAmount,Boolean clientBillable, 
			String fileName,String remarks, Double payableAmount, String financeRemarks,Boolean verified) {
		super();
		this.id = id;
		this.issuedTo = issuedTo;
		this.billDate = billDate;
		this.clientBillable = clientBillable;
		this.giftQuantity = giftQuantity;
		this.verified = verified;
		this.payableAmount = payableAmount;
		this.financeRemarks = financeRemarks;
		this.billCurrency = billCurrency;
		this.sourceAddress = sourceAddress;
		this.destinationAddress = destinationAddress;
		this.totalExpenseAmount = totalExpenseAmount;
		this.claimAmount = claimAmount;
		this.remarks = remarks;
		this.vendor = vendor;
		this.claimCategory = claimCategory;
		this.visitedLocation = visitedLocation;
		this.mode = mode;
		this.rate = rate;
		this.unit = unit;
		this.distance = distance;
		this.fileName = fileName;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	//Getter And Setter
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getParticulars() {
		return particulars;
	}
	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}
	public Double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getTotalExpenseAmount() {
		return totalExpenseAmount;
	}
	public void setTotalExpenseAmount(Double totalExpenseAmount) {
		this.totalExpenseAmount = totalExpenseAmount;
	}

	public ClaimSubmitHeader getClaimHeaderId() {
		return claimHeaderId;
	}

	public void setClaimHeaderId(ClaimSubmitHeader claimHeaderId) {
		this.claimHeaderId = claimHeaderId;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getIssuedTo() {
		return issuedTo;
	}

	public void setIssuedTo(String issuedTo) {
		this.issuedTo = issuedTo;
	}

	
	
	
	
	
}
