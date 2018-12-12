package com.quadcore.lively.parser.model;

import java.util.Date;

public class TwitterDataTransferObject {
	private String screenName;
	private Date createdAt;
	private String text;
	
	public TwitterDataTransferObject() {
		
	}
	
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TwitterDataTransferObject [screenName=").append(screenName).append(", createdAt=")
				.append(createdAt).append(", text=").append(text).append("]");
		return builder.toString();
	}
	
}
