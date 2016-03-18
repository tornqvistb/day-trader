package se.arctisys.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import se.arctisys.constants.TradeConstants;

@Entity
public class Email {

	private Long id;
	private String sender;
	private String receiver;
	private String subject;
	private String content;
	private String status;
	private Date creationDate;
	
	public Email() {
		super();
		creationDate = new Date();
		status = TradeConstants.EMAIL_STATUS_NEW;
		content = "";
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@Transient
	public void addContent(String newContent) {
		this.content = this.content + newContent + "\n";
	}
	@Transient
	public boolean hasContent() {
		boolean result = false;
		if (StringUtils.isNotEmpty(content)) {
			result = true;
		}
		return result;
	}
}
