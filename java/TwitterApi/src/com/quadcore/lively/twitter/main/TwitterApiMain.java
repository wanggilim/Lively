package com.quadcore.lively.twitter.main;

import com.quadcore.lively.twitter.helper.DatabaseHelper;
import com.quadcore.lively.twitter.helper.TwitterHelper;
import com.quadcore.lively.twitter.util.UtilDateToSqlDate;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterApiMain {
	
	public static void main(String[] args) {
		
		DatabaseHelper helper = new DatabaseHelper();
		
		Twitter twitter = new TwitterHelper().getTwitter();
		
		try {
			String screenName = ""; // 검색할 트윗 스크린네임/계정명
			
			Paging p = new Paging();
			p.setCount(200); // 최대 출력 갯수 (200개)
			
			ResponseList<Status> res = twitter.getUserTimeline(screenName, p); // 타임라인 중 가장 최근 내용들
			
			// Celleb 추가
			Status userStatus = res.get(0);
			helper.insertTwitterCelleb(userStatus.getUser().getName());
			
			int twitterCellebId = helper.selectNameOfTwitterCelleb(userStatus.getUser().getName());
			
			// Celleb 의 Timeline 조사 후 DB 삽입
			int result = 0;
			for (int i = 0; i < res.size(); i++) {
				Status status = res.get(i);
				
				//System.out.println(i + ":" + status.getUser().getScreenName()); // 트윗 작성자 -> 스크린네임
				
				result += helper.insertTwitterStatement(status.getText(), 	// mention stmt
						UtilDateToSqlDate.change(status.getCreatedAt()),	// mention date
						"t",												// SNS 구분자
						status.getUser().getLocation(), 					// 위치
						0, 0, 												// likes, sharing
						twitterCellebId 									// cellebno
				);
				
			}
			System.out.println(result +"건 반영");
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
