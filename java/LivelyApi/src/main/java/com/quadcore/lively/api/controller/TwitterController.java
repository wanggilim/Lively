package com.quadcore.lively.api.controller;

import com.quadcore.lively.api.twitter.model.TwitterInstanceBuilder;
import com.quadcore.lively.api.twitter.service.CrawlService;

import twitter4j.Twitter;
import twitter4j.conf.Configuration;

public class TwitterController {
	
	private static final Twitter twitter = TwitterInstanceBuilder.getInstance();
	private static final Configuration config = TwitterInstanceBuilder.getConfiguration();
	
	private CrawlService crawl;
	
	public TwitterController() {
		crawl = new CrawlService();
	}
	
	public final Twitter getTwitter() {
		return twitter;
	}
	
	public void targetCrawl() {
		crawl.target(twitter);
	}
	
	public void randomCrawl() {
		crawl.random(config);
	}
	
}


