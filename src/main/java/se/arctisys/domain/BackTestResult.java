package se.arctisys.domain;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import se.arctisys.util.Util;
@Entity
public class BackTestResult {

	private Long id;
	private Double startValue;	
	private Double endValueAvailable;
	private Double endValueInStocks;
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getStartValue() {
		if (startValue != null)
			return Util.formatDouble(startValue);
		else 
			return startValue;
	}
	public void setStartValue(Double startValue) {
		this.startValue = startValue;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public Double getEndValueAvailable() {
		if (endValueAvailable != null)
			return Util.formatDouble(endValueAvailable);
		else 
			return endValueAvailable;
	}
	public void setEndValueAvailable(Double endValueAvailable) {
		this.endValueAvailable = endValueAvailable;
	}
	public Double getEndValueInStocks() {
		if (endValueInStocks != null)
			return Util.formatDouble(endValueInStocks);
		else
			return endValueInStocks;
	}
	public void setEndValueInStocks(Double endValueInStocks) {
		this.endValueInStocks = endValueInStocks;
	}
	@Transient
	public Double getEndValue() {
		return Util.formatDouble(endValueAvailable + endValueInStocks);
	}
	@Transient
	public Double getProfitAmount() {
		return Util.formatDouble(getEndValue() - startValue);
	}
	@Transient
	public String getProfitPercentage() {
		if (startValue > 0 && getEndValue() > 0) {
			return Util.formatDouble(getProfitAmount()/startValue * 100) + " %";
		} else {
			return "0 %";
		}
	}
	
}
