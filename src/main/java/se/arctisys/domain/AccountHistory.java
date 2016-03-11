package se.arctisys.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class AccountHistory {
	private Long id;
	private Account account;
	private Date actualDate;
	private Double actualBalance;
	private Date creationDate;
	
	public AccountHistory() {
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
	@ManyToOne()
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Date getActualDate() {
		return actualDate;
	}
	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}
	public Double getActualBalance() {
		return actualBalance;
	}
	public void setActualBalance(Double actualBalance) {
		this.actualBalance = actualBalance;
	}

	
}
