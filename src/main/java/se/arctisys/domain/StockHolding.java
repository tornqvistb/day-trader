package se.arctisys.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class StockHolding {
	private Long id;
	private Double lastBuyRate;
	private Double amount;
	private Long numberOfShares;
	private Date creationDate;
	private UserShare userShare;

	public Long getNumberOfShares() {
		return numberOfShares;
	}
	public void setNumberOfShares(Long numberOfShares) {
		this.numberOfShares = numberOfShares;
	}
	
	public StockHolding() {
		super();
		this.creationDate = new Date();
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	@ManyToOne()
	public UserShare getUserShare() {
		return userShare;
	}
	public void setUserShare(UserShare userShare) {
		this.userShare = userShare;
	}
	
}
