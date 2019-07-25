package com.marlabs.rmbs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "currency_master")
public class CurrencyMaster {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private int id;
	  
	  private String currencyCode;
	  private String currencyDesc;
	  
//	  private Double exchangeRateToUSD;
//	  private Double exchangeRateToRupee;
	  
/*	 public Double getExchangeRateToRupee() {
		return exchangeRateToRupee;
	}
	public void setExchangeRateToRupee(Double exchangeRateToRupee) {
		this.exchangeRateToRupee = exchangeRateToRupee;
	}*/
	public String getCurrencyCode() {
		return currencyCode;
	 }
	 public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	 }
	 public String getCurrencyDesc() {
		return currencyDesc;
	 }
	 public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	 }
	 public int getId() {
		return id;
	 }
	 public void setId(int id) {
		this.id = id;
	 }
	 
	/* public Double getExchangeRateToUSD() {
		return exchangeRateToUSD;
	 }
	 public void setExchangeRateToUSD(Double exchangeRateToUSD) {
		this.exchangeRateToUSD = exchangeRateToUSD;
	 }
	 */
	 
	 
}
