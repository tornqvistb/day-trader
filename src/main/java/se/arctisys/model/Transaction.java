package se.arctisys.model;

import java.util.Date;

import se.arctisys.util.Util;

public class Transaction {

	private Long noOfShares;
	private Double amount;
	private String type ;
	private Date transactionDate;
	private Double rate;
	public Long getNoOfShares() {
		return noOfShares;
	}
	public void setNoOfShares(Long noOfShares) {
		this.noOfShares = noOfShares;
	}
	public Double getAmount() {
		return Util.formatDouble(amount);
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Double getRate() {
		return Util.formatDouble(rate);
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
}
