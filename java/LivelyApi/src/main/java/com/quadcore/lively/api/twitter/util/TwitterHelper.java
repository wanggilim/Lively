package com.quadcore.lively.api.twitter.util;

import com.quadcore.lively.api.twitter.config.TwitterConfiguration;

import twitter4j.Twitter;

public class TwitterHelper {
	private Twitter twitter;
	
	public TwitterHelper() {
		twitter = TwitterConfiguration.getInstance();

	}
	
	public Twitter getTwitter() {
		return twitter;
	}

}
