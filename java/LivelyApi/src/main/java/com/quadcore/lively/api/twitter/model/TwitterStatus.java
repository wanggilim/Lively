package com.quadcore.lively.api.twitter.model;

import java.util.List;
import java.util.Map;

public class TwitterStatus {
	
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
	
	public TwitterStatus(String screenName, long statusId, String language, String text, long favoriteCount,
			long retweetCount, long createdAt, long length, long bytes, Map<String, Long> wordcounts,
			List<String> hashtags, Map<String, String> mentions, TwitterGeoLoc geoloc) {
		super();
		this.screenName = screenName;
		this.statusId = statusId;
		this.language = language;
		this.text = text;
		this.favoriteCount = favoriteCount;
		this.retweetCount = retweetCount;
		this.createdAt = createdAt;
		this.length = length;
		this.bytes = bytes;
		this.wordcounts = wordcounts;
		this.hashtags = hashtags;
		this.mentions = mentions;
		this.geoloc = geoloc;
	}


	public String getScreenName() {
		return screenName;
	}


	public long getStatusId() {
		return statusId;
	}


	public String getLanguage() {
		return language;
	}


	public String getText() {
		return text;
	}


	public long getRetweetCount() {
		return retweetCount;
	}


	public long getCreatedAt() {
		return createdAt;
	}


	public long getLength() {
		return length;
	}


	public long getBytes() {
		return bytes;
	}


	public Map<String, Long> getWordcounts() {
		return wordcounts;
	}


	public List<String> getHashtags() {
		return hashtags;
	}

	public Map<String, String> getMentions() {
		return mentions;
	}

	public TwitterGeoLoc getGeoloc() {
		return geoloc;
	}


	public long getFavoriteCount() {
		return favoriteCount;
	}
	
	
	
	
	
}
