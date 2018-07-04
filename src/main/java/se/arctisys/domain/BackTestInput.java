package se.arctisys.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
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
	
	@NotEmpty(message = "*Välj en aktie")
	private String shareId;
	
	@NotNull(message="*Ange ett belopp")
	private Integer amount;
	
	@NotEmpty(message = "*Välj en strategi")
	private String strategyId;

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
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	public String getStrategyId() {
		return strategyId;
	}
	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
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
	
}
