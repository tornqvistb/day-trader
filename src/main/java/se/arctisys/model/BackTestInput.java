package se.arctisys.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class BackTestInput {	
	
	@NotNull(message = "*Välj ett startdatum")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@NotNull(message = "*Välj ett slutdatum")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@NotEmpty(message = "*Välj en aktie")
	private String shareId;
	
	@NotNull(message="*Ange ett belopp")
	private Double amount;
	
	@NotEmpty(message = "*Välj en strategi")
	private String strategyId;
	
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}	
	
}
