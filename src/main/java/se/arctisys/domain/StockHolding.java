package se.arctisys.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class StockHolding {
	private String id;
	private Share share;
	private Double lastBuyRate;
	private Double amount;
	private Integer numberOfShares;
	private Date creationDate;

	public Integer getNumberOfShares() {
		return numberOfShares;
	}
	public void setNumberOfShares(Integer numberOfShares) {
		this.numberOfShares = numberOfShares;
	}
	
	public StockHolding() {
		super();
		this.creationDate = new Date();
	}
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	@ManyToOne()
	public Share getShare() {
		return share;
	}
	public void setShare(Share share) {
		this.share = share;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@Transient
	public String getCreationDateDisplay() {		
		String result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(creationDate);
		return result;		
	}
	public Double getLastBuyRate() {
		return lastBuyRate;
	}
	public void setLastBuyRate(Double lastBuyRate) {
		this.lastBuyRate = lastBuyRate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
