package se.arctisys.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class BackTestInput {	
	
	private Long id;
	
	@NotNull(message = "*Välj ett startdatum")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@NotNull(message = "*Välj ett slutdatum")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	private Share share;
	
	@NotNull(message="*Ange ett belopp")
	private Integer amount;
	
	private Strategy strategy;

	private BackTestJob backTestJob; 
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@ManyToOne
	public BackTestJob getBackTestJob() {
		return backTestJob;
	}
	public void setBackTestJob(BackTestJob backTestJob) {
		this.backTestJob = backTestJob;
	}
	@ManyToOne
	public Share getShare() {
		return share;
	}
	public void setShare(Share share) {
		this.share = share;
	}
	@ManyToOne
	public Strategy getStrategy() {
		return strategy;
	}
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	@Transient
	public String getStartDateReadable() {	
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		return sdfDate.format(startDate);
	}
	@Transient
	public String getEndDateReadable() {	
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		return sdfDate.format(endDate);
	}
	
}
