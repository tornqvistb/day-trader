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
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
public class UserShare {

	private Long id;
	private TradingUser tradingUser;
	private Share share;
	private Date creationDate;
	private Date lastBuyDate;
	private Date lastSellDate;
	private Long buyAmount;
	private Long frequency;
	private Set<StockHolding> stockHoldings = new HashSet<StockHolding>();
	private Set<ShareTransaction> transactions = new HashSet<ShareTransaction>();
	
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
	public TradingUser getTradingUser() {
		return tradingUser;
	}

	public void setTradingUser(TradingUser tradingUser) {
		this.tradingUser = tradingUser;
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

	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="userShare")
	public Set<StockHolding> getStockHolding() {
		return stockHoldings;
	}
	public void setStockHolding(Set<StockHolding> stockHoldings) {
		this.stockHoldings = stockHoldings;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="userShare")
	@OrderBy("creationDate")
	public Set<ShareTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Set<ShareTransaction> transactions) {
		this.transactions = transactions;
	}
	@Transient
	public boolean hasStockHolding() {
		boolean result = false;
		if (this.stockHoldings != null && this.stockHoldings.size() > 0) {
			result = true;
		}
		return result;
	}
	@Transient
	public StockHolding getMainStockHolding() {
		StockHolding holding = null;
		if (stockHoldings.size() > 0) {
			for (StockHolding sh : stockHoldings) {
				holding = sh;
				break;
			}
		}
		return holding;
	}
}