package se.arctisys.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Strategy {
	private String id;
	private String description;
	private Boolean compareLongToMedium;
	private Double multipleLongToMedium;
	private Boolean compareMediumToShort;
	private Double multipleMediumToShort;
	private Boolean compareShortToCurrent;
	private Double multipleShortToCurrent;
	private Date creationDate;
	
	public Strategy() {
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getCompareLongToMedium() {
		return compareLongToMedium;
	}
	public void setCompareLongToMedium(Boolean compareLongToMedium) {
		this.compareLongToMedium = compareLongToMedium;
	}
	public Double getMultipleLongToMedium() {
		return multipleLongToMedium;
	}
	public void setMultipleLongToMedium(Double multipleLongToMedium) {
		this.multipleLongToMedium = multipleLongToMedium;
	}
	public Boolean getCompareMediumToShort() {
		return compareMediumToShort;
	}
	public void setCompareMediumToShort(Boolean compareMediumToShort) {
		this.compareMediumToShort = compareMediumToShort;
	}
	public Double getMultipleMediumToShort() {
		return multipleMediumToShort;
	}
	public void setMultipleMediumToShort(Double multipleMediumToShort) {
		this.multipleMediumToShort = multipleMediumToShort;
	}
	public Boolean getCompareShortToCurrent() {
		return compareShortToCurrent;
	}
	public void setCompareShortToCurrent(Boolean compareShortToCurrent) {
		this.compareShortToCurrent = compareShortToCurrent;
	}
	public Double getMultipleShortToCurrent() {
		return multipleShortToCurrent;
	}
	public void setMultipleShortToCurrent(Double multipleShortToCurrent) {
		this.multipleShortToCurrent = multipleShortToCurrent;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
