package se.arctisys.model;

import java.util.Date;

public class Transaction {

	private Integer noOfShares;
	private Double amount;
	private String type ;
	private Date transactionDate;
	public Integer getNoOfShares() {
		return noOfShares;
	}
	public void setNoOfShares(Integer noOfShares) {
		this.noOfShares = noOfShares;
	}
	public Double getAmount() {
		return amount;
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
	
}
