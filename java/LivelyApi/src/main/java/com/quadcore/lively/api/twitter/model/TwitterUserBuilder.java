package com.quadcore.lively.api.twitter.model;

public class TwitterUserBuilder {
	
	private long id = 0;
	private boolean isVerified = false;
	private String name = "";
	private String screenName = "";
	private String url = "";
	private String profileUrl = "";
	private String biggerProfileUrl = "";
	private int friendsCount = 0;
	private int followersCount = 0;
	private int favoritesCount = 0;
	private String email = "";
	private TwitterGeoLoc geoLocation;
	private long createdAt = 0;
	private String serviceName = "Twitter";
	
	public TwitterUserBuilder setId(long id) {
		this.id = id;
		return this;
	}
	
	public TwitterUserBuilder setVerified(boolean isVerified) {
		this.isVerified = isVerified;
		return this;
	}
	
	public TwitterUserBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public TwitterUserBuilder setScreenName(String screenName) {
		this.screenName = screenName;
		return this;
	}

	public TwitterUserBuilder setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
		return this;
	}

	public TwitterUserBuilder setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
		return this;
	}

	public TwitterUserBuilder setFavoritesCount(int favoritesCount) {
		this.favoritesCount = favoritesCount;
		return this;
	}

	public TwitterUserBuilder setGeoLocation(TwitterGeoLoc geoLocation) {
		this.geoLocation = geoLocation;
		return this;
	}
	
	public TwitterUserBuilder setUrl(String url) {
		this.url = url;
		return this;
	}

	public TwitterUserBuilder setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
		return this;
	}
	public TwitterUserBuilder setBiggerProfileUrl(String biggerProfileUrl) {
		this.biggerProfileUrl = biggerProfileUrl;
		return this;
	}
	
	public TwitterUserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public TwitterUserBuilder setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	
	public TwitterUser build() {
		TwitterUser user
		= new TwitterUser(id, isVerified, name, screenName, url, profileUrl, biggerProfileUrl, friendsCount, followersCount, favoritesCount, email, createdAt, geoLocation)
		;
		return user; 
	}

	
	

}
