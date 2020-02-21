package se.arctisys.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import se.arctisys.util.Util;

@Entity
public class Transaction {

	private Long id;
	private Long noOfShares;
	private Double amount;
	private String type ;
	private Date transactionDate;
	private Double rate;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNoOfShares() {
		return noOfShares;
	}
	public void setNoOfShares(Long noOfShares) {
		this.noOfShares = noOfShares;
	}
	public Double getAmount() {
		if (amount != null)
			return Util.formatDouble(amount);
		else
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
	public Double getRate() {
		if (rate != null)
			return Util.formatDouble(rate);
		else
			return rate;				
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
}
