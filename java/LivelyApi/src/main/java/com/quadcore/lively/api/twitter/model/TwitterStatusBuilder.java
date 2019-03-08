package com.quadcore.lively.api.twitter.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitterStatusBuilder {
	
	private String screenName = "";			// screenName
	private long statusId = 0;				// Twitter 네트워크 상 Status Id
	private long createdAt = 0;				// Status 작성일시
	private String language = "";			// Status의 언어
	private String text = "";				// raw text
	private long retweetCount = 0;			// retweetcount
	private long length = 0;				// sentence의 글자 길이
	private long bytes = 0;					// sentence의 바이트 수
	private Map<String, Long> wordcounts;	// word, count 쌍으로 이루어진 Map
	private List<String> hashtags;			// HashTag
	private Map<String, String> mentions;	// mentions
	private TwitterGeoLoc geoloc;			// geolocation
	private long favoriteCount = 0;			// favoriteCount
	
	public TwitterStatusBuilder() {
		wordcounts = new HashMap<>(1);
		hashtags = new ArrayList<>(1);
	}
	
	public TwitterStatusBuilder setScreenName(String screenName) {
		this.screenName = screenName;
		return this;
	}

	public TwitterStatusBuilder setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public TwitterStatusBuilder setRetweetCount(long retweetCount) {
		this.retweetCount = retweetCount;
		return this;
	}

	public TwitterStatusBuilder setStatusId(long statusId) {
		this.statusId = statusId;
		return this;
	}
	public TwitterStatusBuilder setLanguage(String language) {
		this.language = language;
		return this;
	}
	public TwitterStatusBuilder setText(String text) {
		this.text = text;
		return this;
	}
	public TwitterStatusBuilder setLength(long length) {
		this.length = length;
		return this;
	}
	public TwitterStatusBuilder setBytes(long bytes) {
		this.bytes = bytes;
		return this;
	}
	
	public TwitterStatusBuilder setWordcounts(Map<String, Long> wordcounts) {
		this.wordcounts = wordcounts;
		return this;
	}
	
	public TwitterStatusBuilder setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
		return this;
	}
	
	public TwitterStatusBuilder setMentions(Map<String, String> mentions) {
		this.mentions = mentions;
		return this;
	}
	
	public TwitterStatusBuilder setFavoriteCount(long favoriteCount) {
		this.favoriteCount = favoriteCount;
		return this;
	}
	
	public TwitterStatusBuilder setGeoLocation(TwitterGeoLoc geoLoc) {
		this.geoloc = geoLoc;
		return this;
	}
	
	public TwitterStatus build() {
		TwitterStatus status
		= new TwitterStatus(screenName, statusId, language, text, favoriteCount, retweetCount, createdAt, length, bytes, wordcounts, hashtags, mentions, geoloc)
		;
		
		return status;
	}

	

	
	
}
