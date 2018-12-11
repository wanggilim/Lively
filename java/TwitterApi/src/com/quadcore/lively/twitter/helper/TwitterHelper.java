package com.quadcore.lively.twitter.helper;

import com.quadcore.lively.twitter.config.TwitterConfiguration;

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
