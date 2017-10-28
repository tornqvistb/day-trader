package se.arctisys.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
public class Account {
	private Long id;
	private Double actualBalance;
	private Double minimumBalance;
	private Set<AccountHistory> accountHistory = new HashSet<AccountHistory>();
	private Date creationDate;
	private TradingUser tradingUser;
	
	public Account() {
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
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="account")
	@OrderBy("creationDate")
	public Set<AccountHistory> getAccountHistory() {
		return accountHistory;
	}
	public void setAccountHistory(Set<AccountHistory> accountHistory) {
		this.accountHistory = accountHistory;
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
	public Double getActualBalance() {
		return actualBalance;
	}
	public void setActualBalance(Double actualBalance) {
		this.actualBalance = actualBalance;
	}
	public Double getMinimumBalance() {
		return minimumBalance;
	}
	public void setMinimumBalance(Double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	@Transient
	public void increaseBalance(Double increase) {
		this.actualBalance = actualBalance + increase;
	}
	@Transient
	public void decreaseBalance(Double decrease) {
		this.actualBalance = actualBalance - decrease;
	}
	@OneToOne
	public TradingUser getTradingUser() {
		return tradingUser;
	}
	public void setTradingUser(TradingUser tradingUser) {
		this.tradingUser = tradingUser;
	}
	
}
