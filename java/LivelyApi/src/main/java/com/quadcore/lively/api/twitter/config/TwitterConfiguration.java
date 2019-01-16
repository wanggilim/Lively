package com.quadcore.lively.api.twitter.config;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfiguration {
	private static final String consumerKey = "";
	private static final String consumerSecret = "";
	private static final String accessToken = "";
	private static final String accessTokenSecret = "";

	public static Twitter getInstance() {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true)
		        .setOAuthConsumerKey(consumerKey)
		        .setOAuthConsumerSecret(consumerSecret)
		        .setOAuthAccessToken(accessToken)
		        .setOAuthAccessTokenSecret(accessTokenSecret)
				.setTweetModeExtended(true);
		TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
		Twitter twitter = tf.getInstance();
		return twitter;
	}
}
