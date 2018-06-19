package se.arctisys.model;

public class BackTestHolding {

	private Long noOfShares = 0L;	
	private Double available = 0.0;
	private Double lastBuyRate;
	public Long getNoOfShares() {
		return noOfShares;
	}
	public void setNoOfShares(Long noOfShares) {
		this.noOfShares = noOfShares;
	}
	public Double getAvailable() {
		return available;
	}
	public void setAvailable(Double available) {
		this.available = available;
	}
	public Double getLastBuyRate() {
		return lastBuyRate;
	}
	public void setLastBuyRate(Double lastBuyRate) {
		this.lastBuyRate = lastBuyRate;
	}

}
