package se.arctisys.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import se.arctisys.constants.TradeConstants;

@Entity
public class ShareOnMarket {
	private String id;
	private String description;
	private Date creationDate;
	private String status;
	private String currency = TradeConstants.CURRENCY_SEK;
	
	public ShareOnMarket() {
		super();
		this.creationDate = new Date();
	}
	public ShareOnMarket(String id, String description) {
		super();
		this.id = id;
		this.description = description;
		this.status = TradeConstants.SHARE_STATUS_NEW;
		this.creationDate = new Date();
	}
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
