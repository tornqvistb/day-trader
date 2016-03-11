package se.arctisys.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class ShareTransaction {
	private String id;
	private Share share;
	private String type;	
	private Date actualDate;
	private Double amount;
	private String status;
	private Date creationDate;
	
	public ShareTransaction() {
		super();
		this.creationDate = new Date();
	}
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@ManyToOne()
	public Share getShare() {
		return share;
	}
	public void setShare(Share share) {
		this.share = share;
	}
	public Date getActualDate() {
		return actualDate;
	}
	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
