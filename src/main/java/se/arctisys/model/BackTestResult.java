package se.arctisys.model;

import java.util.List;

public class BackTestResult {

	private Double startValue;	
	private Double endValue;
	private List<Transaction> transactions;
	public Double getStartValue() {
		return startValue;
	}
	public void setStartValue(Double startValue) {
		this.startValue = startValue;
	}
	public Double getEndValue() {
		return endValue;
	}
	public void setEndValue(Double endValue) {
		this.endValue = endValue;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public Double getProfitAmount() {
		return endValue - startValue;
	}
	public String getProfitPercentage() {
		if (startValue > 0 && endValue > 0) {
			return getProfitAmount()/startValue * 100 + " %";
		} else {
			return "0 %";
		}
	}
	
}
