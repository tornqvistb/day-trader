package se.arctisys.model;

import java.util.ArrayList;
import java.util.List;

import se.arctisys.domain.SystemProperty;

public class RequestAttributes {

	private String thanksMessage;
	private Integer newErrorMessages;
	private List<SystemProperty> systemProperties= new ArrayList<SystemProperty>();
	
	public RequestAttributes() {
		super();
	}
	public RequestAttributes(Integer newErrorMessages) {
		super();
		this.newErrorMessages = newErrorMessages;
	}

	public String getThanksMessage() {
		return thanksMessage;
	}

	public void setThanksMessage(String thanksMessage) {
		this.thanksMessage = thanksMessage;
	}

	public Integer getNewErrorMessages() {
		return newErrorMessages;
	}

	public void setNewErrorMessages(Integer newErrorMessages) {
		this.newErrorMessages = newErrorMessages;
	}
	public List<SystemProperty> getSystemProperties() {
		return systemProperties;
	}
	public void setSystemProperties(List<SystemProperty> systemProperties) {
		this.systemProperties = systemProperties;
	}
	
	
}
