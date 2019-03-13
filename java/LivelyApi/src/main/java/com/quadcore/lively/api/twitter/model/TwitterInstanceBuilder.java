package com.quadcore.lively.api.twitter.model;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterInstanceBuilder {
	private static final String consumerKey = "";
	private static final String consumerSecret = "";
	private static final String accessToken = "";
	private static final String accessTokenSecret = "";
	
	public static Configuration getConfiguration() {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true)
		        .setOAuthConsumerKey(consumerKey)
		        .setOAuthConsumerSecret(consumerSecret)
		        .setOAuthAccessToken(accessToken)
		        .setOAuthAccessTokenSecret(accessTokenSecret)
				.setTweetModeExtended(true);
		return configurationBuilder.build();
	}

	public static Twitter getInstance() {
		TwitterFactory tf = new TwitterFactory(getConfiguration());
		Twitter twitter = tf.getInstance();
		return twitter;
	}
}
