package com.quadcore.lively.twitter.main;

import com.quadcore.lively.twitter.helper.TwitterHelper;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterApiMain {
	
	public static void main(String[] args) {
		
		Twitter twitter = new TwitterHelper().getTwitter();
		
		try {
			String screenName = "realDonaldTrump"; // 트윗 스크린네임/계정명
			
			Paging p = new Paging();
			p.setCount(200); // 최대 출력 갯수 (200개)
			
			ResponseList<Status> res = twitter.getUserTimeline(screenName, p); // 타임라인 중 가장 최근 내용들
			
			
			for (int i = 0; i < res.size(); i++) {
				Status status = res.get(i);
				System.out.println(i + ":" + status.getUser().getScreenName()); // 트윗 작성자 -> 스크린네임
				System.out.println(status.getText().toString()); // 트윗 맨션
				System.out.println(status.getCreatedAt().getTime()); // 시간 (long)
				System.out.println(status.getCreatedAt().toString()); // 시간 (Date)
				System.out.println(status.getUser().getLocation()); // 위치 (ex: Washington DC)
				System.out.println();
			}
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}
