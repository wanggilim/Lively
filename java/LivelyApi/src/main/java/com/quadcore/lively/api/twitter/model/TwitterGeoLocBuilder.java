package com.quadcore.lively.api.twitter.model;

public class TwitterGeoLocBuilder {
	private boolean isEnabled = false;
	private double latitude = 0;
	private double longitude = 0;
	private String country = ""; 
	private String location = "";
	
	public TwitterGeoLocBuilder setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}
	public TwitterGeoLocBuilder setLatitude(double latitude) {
		this.latitude = latitude;
		return this;
	}
	public TwitterGeoLocBuilder setLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}
	public TwitterGeoLocBuilder setCountry(String country) {
		this.country = country;
		return this;
	}
	public TwitterGeoLocBuilder setLocation(String location) {
		this.location = location;
		return this;
	}
	
	public TwitterGeoLoc build() {
		TwitterGeoLoc geoLocation = new TwitterGeoLoc(isEnabled, latitude, longitude, country, location);
		return geoLocation;
	}
	
	
}
