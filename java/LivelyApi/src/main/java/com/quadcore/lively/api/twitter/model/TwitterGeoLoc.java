package com.quadcore.lively.api.twitter.model;

public class TwitterGeoLoc {
	private boolean isEnabled = false;
	private double latitude = 0;
	private double longitude = 0;
	private String country = ""; 
	private String location = "";
	
	public TwitterGeoLoc(boolean isEnabled, double latitude, double longitude, String country, String location) {
		super();
		this.isEnabled = isEnabled;
		this.latitude = latitude;
		this.longitude = longitude;
		this.country = country;
		this.location = location;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getCountry() {
		return country;
	}

	public String getLocation() {
		return location;
	}
	
	
}
