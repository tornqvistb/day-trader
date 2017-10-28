package se.arctisys.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
public class Share {
	private String id;
	private String description;
	private Set<ShareDayRate> dayRates = new HashSet<ShareDayRate>();
	private Date creationDate;
	private ShareOnMarket shareOnMarket;
	
	public Share() {
		super();
		this.creationDate = new Date();
	}
	

	public Share(String id, String description, ShareOnMarket shareOnMarket) {
		super();
		this.id = id;
		this.description = description;
		this.shareOnMarket = shareOnMarket;
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
	@ManyToOne
	public ShareOnMarket getShareOnMarket() {
		return shareOnMarket;
	}

	public void setShareOnMarket(ShareOnMarket shareOnMarket) {
		this.shareOnMarket = shareOnMarket;
	}

}
