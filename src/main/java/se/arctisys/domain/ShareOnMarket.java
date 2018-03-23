package se.arctisys.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import se.arctisys.constants.TradeConstants;

@Entity
public class ShareOnMarket {
	private String id;
	private String name;
	private String currency = TradeConstants.CURRENCY_SEK;
	private String sector;
	private String url;
	private Date creationDate;
	private String status;
	
	public ShareOnMarket() {
		this.creationDate = new Date();
	}
	
	public ShareOnMarket(String id, String name, String currency, String sector, String url) {
		super();
		this.id = id;
		this.name = name;
		this.currency = currency;
		this.sector = sector;
		this.url = url;
		this.creationDate = new Date();
		this.status = TradeConstants.SHARE_STATUS_NEW;
	}

	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
