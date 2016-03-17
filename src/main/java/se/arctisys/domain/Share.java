package se.arctisys.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import se.arctisys.constants.TradeConstants;

@Entity
public class Share {
	private String id;
	private String strategy;
	private String description;
	private String currency;
	private Set<ShareDayRate> dayRates = new HashSet<ShareDayRate>();
	private Date creationDate;
	
	public Share() {
		super();
		this.creationDate = new Date();
		this.strategy = TradeConstants.STRATEGY_LONG;
		this.currency = TradeConstants.CURRENCY_SEK;		
	}
	public Share(String id, String strategy, String description, String currency) {
		super();
		this.id = id;
		this.strategy = strategy;
		this.description = description;
		this.currency = currency;
		this.creationDate = new Date();
	}
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
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
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="share")
	@OrderBy("actualDate DESC")
	public Set<ShareDayRate> getDayRates() {
		return dayRates;
	}
	public void setDayRates(Set<ShareDayRate> dayRates) {
		this.dayRates = dayRates;
	}
	@Transient
	public ShareDayRate getLastDayRate() {		
		ShareDayRate dayRate = null;
		if (dayRates != null && dayRates.size() > 0) {
			for (ShareDayRate rate : dayRates) {
				dayRate = rate;
				break;
			}
		}
		return dayRate;
	}
	@Transient
	public Long getDefaultFrequency() {
		//TODO get this from sys user instead
		Long result = 4L;
		return result;
	}

}
