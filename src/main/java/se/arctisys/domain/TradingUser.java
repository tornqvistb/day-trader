package se.arctisys.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class TradingUser {
	private String userId;
	private String name;
	private String email;
	private Date creationDate;
	private Set<Account> accounts = new HashSet<Account>();
	private Set<UserShare> userShares = new HashSet<UserShare>();	
	
	public TradingUser() {
		super();
		this.creationDate = new Date();
	}

	@Id
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="tradingUser")
	public Set<Account> getAccount() {
		return accounts;
	}

	public void setAccount(Set<Account> accounts) {
		this.accounts = accounts;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="tradingUser")
	public Set<UserShare> getUserShares() {
		return userShares;
	}

	public void setUserShares(Set<UserShare> userShares) {
		this.userShares = userShares;
	}	
	@Transient
	public Account getMainAccount() {
		Account account = null;
		if (accounts.size() > 0) {
			for (Account acc : accounts) {
				account = acc;
				break;
			}
		}
		return account;
	}
	
	
}