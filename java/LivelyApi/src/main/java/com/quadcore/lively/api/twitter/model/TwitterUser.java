package com.quadcore.lively.api.twitter.model;

public class TwitterUser {
	
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
	
	public TwitterUser(long id, boolean isVerified, String name, String screenName, String url, String profileUrl,
			String biggerProfileUrl, int friendsCount, int followersCount, int favoritesCount, String email,
			long createdAt, TwitterGeoLoc geoLocation) {
		super();
		this.id = id;
		this.isVerified = isVerified;
		this.name = name;
		this.screenName = screenName;
		this.url = url;
		this.profileUrl = profileUrl;
		this.biggerProfileUrl = biggerProfileUrl;
		this.friendsCount = friendsCount;
		this.followersCount = followersCount;
		this.favoritesCount = favoritesCount;
		this.email = email;
		this.createdAt = createdAt;
		this.geoLocation = geoLocation;
	}

	public String getServiceName() {
		return serviceName;
	}



	public long getId() {
		return id;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public String getName() {
		return name;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getUrl() {
		return url;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public String getBiggerProfileUrl() {
		return biggerProfileUrl;
	}

	public int getFriendsCount() {
		return friendsCount;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public int getFavoritesCount() {
		return favoritesCount;
	}

	public String getEmail() {
		return email;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public TwitterGeoLoc getGeoLocation() {
		return geoLocation;
	}

	
	
}
