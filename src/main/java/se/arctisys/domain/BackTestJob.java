package se.arctisys.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import se.arctisys.constants.TradeConstants;

@Entity
public class BackTestJob {

	private Long id;
	private String name;
	private String status;
	private Date executionDate;
	private Set<BackTestInput> inputList  = new HashSet<BackTestInput>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	@Transient
	public String getStatusReadable() {
		String result = "";
		if (status != null) {
			if (TradeConstants.JOB_STATUS_NEW.equals(status)) {
				result = TradeConstants.JOB_STATUS_NEW_READABLE;
			} else if (TradeConstants.JOB_STATUS_START.equals(status)) {
				result = TradeConstants.JOB_STATUS_START_READABLE;
			} else if (TradeConstants.JOB_STATUS_DONE.equals(status)) {
				result = TradeConstants.JOB_STATUS_DONE_READABLE;
			} 
		}
		return result;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="backTestJob")
	public Set<BackTestInput> getInputList() {
		return inputList;
	}
	public void setInputList(Set<BackTestInput> inputList) {
		this.inputList = inputList;
	}
	
}
