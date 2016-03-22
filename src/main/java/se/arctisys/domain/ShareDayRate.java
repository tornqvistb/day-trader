package se.arctisys.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class ShareDayRate {
	private Long id;
	private Share share;
	private Date actualDate;
	private Double buyRate;
	private Double sellRate;
	private Double minRate;
	private Double maxRate;
	private Double changeThisYearPerc;
	private Double ATH;
	private Double lowFrequencyRate;
	private Double highFrequencyRate;	
	private Double movingAverageShort;
	private Double movingAverageMedium;
	private Double movingAverageLong;
	private Date creationDate;
	private Long tradedVolume;
	
	public ShareDayRate() {
		super();
		this.creationDate = new Date();
	}
	@Transient
	public void setEmptyValues() {
		this.buyRate = 0.0;
		this.sellRate = 0.0;
		this.minRate = 0.0;
		this.maxRate = 0.0;
		this.changeThisYearPerc = 0.0;
		this.ATH = 0.0;
		this.lowFrequencyRate = 0.0;
		this.highFrequencyRate = 0.0;
		this.movingAverageShort = 0.0;
		this.movingAverageMedium = 0.0;
		this.movingAverageLong = 0.0;
		this.tradedVolume = 0L;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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

	public Double getBuyRate() {
		return buyRate;
	}

	public void setBuyRate(Double buyRate) {
		this.buyRate = buyRate;
	}

	public Double getSellRate() {
		return sellRate;
	}

	public void setSellRate(Double sellRate) {
		this.sellRate = sellRate;
	}

	public Double getMinRate() {
		return minRate;
	}

	public void setMinRate(Double minRate) {
		this.minRate = minRate;
	}

	public Double getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(Double maxRate) {
		this.maxRate = maxRate;
	}

	public Double getChangeThisYearPerc() {
		return changeThisYearPerc;
	}

	public void setChangeThisYearPerc(Double changeThisYearPerc) {
		this.changeThisYearPerc = changeThisYearPerc;
	}

	public Double getATH() {
		return ATH;
	}

	public void setATH(Double aTH) {
		ATH = aTH;
	}

	public Double getLowFrequencyRate() {
		return lowFrequencyRate;
	}

	public void setLowFrequencyRate(Double lowFrequencyRate) {
		this.lowFrequencyRate = lowFrequencyRate;
	}

	public Double getHighFrequencyRate() {
		return highFrequencyRate;
	}

	public void setHighFrequencyRate(Double highFrequencyRate) {
		this.highFrequencyRate = highFrequencyRate;
	}

	public Double getMovingAverageShort() {
		return movingAverageShort;
	}

	public void setMovingAverageShort(Double movingAverageShort) {
		this.movingAverageShort = movingAverageShort;
	}

	public Double getMovingAverageMedium() {
		return movingAverageMedium;
	}

	public void setMovingAverageMedium(Double movingAverageMedium) {
		this.movingAverageMedium = movingAverageMedium;
	}

	public Double getMovingAverageLong() {
		return movingAverageLong;
	}

	public void setMovingAverageLong(Double movingAverageLong) {
		this.movingAverageLong = movingAverageLong;
	}
	@Transient
	public boolean isComplete() {
		boolean result = false;
		if (lowFrequencyRate > 0 && highFrequencyRate > 0 && buyRate > 0 && sellRate > 0) {
			result = true;
		}
		return result;
	}
	@Transient
	public boolean inDateRange(Date startDate, Date endDate) {
		boolean result = false;
		if (startDate.before(actualDate) && endDate.after(actualDate)) {
			result = true;
		}
		return result;
	}
	@Transient
	public boolean isBuyCandidate() {
		boolean result = false;
		if (buyRate <= lowFrequencyRate) {
			result = true;
		}
		return result;
	}
	@Transient
	public boolean isSellCandidate() {
		boolean result = false;
		if (sellRate >= highFrequencyRate) {
			result = true;
		}
		return result;
	}
	public Long getTradedVolume() {
		return tradedVolume;
	}
	public void setTradedVolume(Long tradedVolume) {
		this.tradedVolume = tradedVolume;
	}
	
}
