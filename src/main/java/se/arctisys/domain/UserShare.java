package se.arctisys.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.apache.commons.math3.util.Precision;

@Entity
public class UserShare {

	private Long id;
	private User user;
	private Share share;
	private Date creationDate;
	private Date lastBuyDate;
	private Date lastSellDate;
	private Long buyAmount;
	private ShareHolding shareHolding;
	private Set<ShareTransaction> transactions = new HashSet<ShareTransaction>();
	private Strategy strategy;
	
	public UserShare() {
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
	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
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
	public Date getLastBuyDate() {
		return lastBuyDate;
	}
	public void setLastBuyDate(Date lastBuyDate) {
		this.lastBuyDate = lastBuyDate;
	}
	public Date getLastSellDate() {
		return lastSellDate;
	}
	public void setLastSellDate(Date lastSellDate) {
		this.lastSellDate = lastSellDate;
	}

	public Long getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(Long buyAmount) {
		this.buyAmount = buyAmount;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="userShare")
	@OrderBy("creationDate")
	public Set<ShareTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Set<ShareTransaction> transactions) {
		this.transactions = transactions;
	}

	@OneToOne
	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="userShare")
	public ShareHolding getShareHolding() {
		return shareHolding;
	}

	public void setShareHolding(ShareHolding shareHolding) {
		this.shareHolding = shareHolding;
	}
	@Transient
	public boolean hasShareHolding() {
		boolean result = false;
		if (this.shareHolding != null) {
			result = true;
		}
		return result;
	}	
	@Transient
	public Long getNumberOfShares() {
		if (hasShareHolding()) {
			return this.shareHolding.getNumberOfShares();
		} else {
			return 0L;
		}
	}
	@Transient
	public Double getLastShareValue() {
		if (!this.share.getDayRates().isEmpty()) {
			return Precision.round(this.share.getLastDayRate().getBuyRate(), 2);
		} else {
			return 0.0;
		}
	}
	@Transient
	public Double getHoldingValue() {
		if (hasShareHolding()) {
			return Precision.round(this.shareHolding.getNumberOfShares() * getLastShareValue(), 2);
		} else {
			return 0.0;
		}
	}

}