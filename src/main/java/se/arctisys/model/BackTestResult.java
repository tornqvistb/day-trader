package se.arctisys.model;

import java.util.ArrayList;
import java.util.List;

import se.arctisys.util.Util;

public class BackTestResult {

	private Double startValue;	
	private Double endValueAvailable;
	private Double endValueInStocks;
	private List<Transaction> transactions = new ArrayList<Transaction>();
	public Double getStartValue() {
		return Util.formatDouble(startValue);
	}
	public void setStartValue(Double startValue) {
		this.startValue = startValue;
	}
	public Double getEndValue() {
		return Util.formatDouble(endValueAvailable + endValueInStocks);
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public Double getProfitAmount() {
		return Util.formatDouble(getEndValue() - startValue);
	}
	public String getProfitPercentage() {
		if (startValue > 0 && getEndValue() > 0) {
			return Util.formatDouble(getProfitAmount()/startValue * 100) + " %";
		} else {
			return "0 %";
		}
	}
	public Double getEndValueAvailable() {
		return Util.formatDouble(endValueAvailable);
	}
	public void setEndValueAvailable(Double endValueAvailable) {
		this.endValueAvailable = endValueAvailable;
	}
	public Double getEndValueInStocks() {
		return Util.formatDouble(endValueInStocks);
	}
	public void setEndValueInStocks(Double endValueInStocks) {
		this.endValueInStocks = endValueInStocks;
	}
	
}
