package com.quadcore.lively.twitter.config;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfiguration {
	private static final String consumerKey = "90Xt3OsPhlcwRrnHNh5Jsb6aW";
	private static final String consumerSecret = "oGAZrX6W4n4MtcTT5xEvOB5dvzyyQy3Cqm3gAnoHuQfrWh5z7Z";
	private static final String accessToken = "1069561725794443270-UzTZAWbuBKCdsFKxEko0FGMyASFe9y";
	private static final String accessTokenSecret = "Ra1WLEsNBNDGbRFXQV5MRw7buxD8pZeJkbiEpmiLia1Aa";

	public static Twitter getInstance() {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true)
		        .setOAuthConsumerKey(consumerKey)
		        .setOAuthConsumerSecret(consumerSecret)
		        .setOAuthAccessToken(accessToken)
		        .setOAuthAccessTokenSecret(accessTokenSecret);
		TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
		Twitter twitter = tf.getInstance();
		return twitter;
	}
}
