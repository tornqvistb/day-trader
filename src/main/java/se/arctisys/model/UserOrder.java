package se.arctisys.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UserOrder {

	@NotEmpty(message = "*Välj en aktie")
	private String shareId;
	@NotNull(message="*Ange ett belopp")
	private Long amount;
	@NotEmpty(message = "*Välj en strategi")
	private String strategyId;

	public UserOrder() {
	}

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}
}
