package se.arctisys.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import se.arctisys.domain.Transaction;

public class Profit {

	private Double result;
	private Double totalBuy;
	private Double totalSell;
	private List<Transaction> transactions;
	private Date startDate;
	private Date endDate;
	
	public Profit() {
		super();
		this.transactions = new ArrayList<Transaction>();
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getTotalBuy() {
		return totalBuy;
	}
	public void setTotalBuy(Double totalBuy) {
		this.totalBuy = totalBuy;
	}
	public Double getTotalSell() {
		return totalSell;
	}
	public void setTotalSell(Double totalSell) {
		this.totalSell = totalSell;
	}
	@Override
	public String toString() {
		return "Profit [result=" + result + ", totalBuy=" + totalBuy + ", totalSell=" + totalSell + ", transactions="
				+ transactions + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}
