package com.quadcore.lively.api.twitter.dao;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.quadcore.lively.api.twitter.model.TwitterGeoLocBuilder;
import com.quadcore.lively.api.twitter.model.TwitterStatus;
import com.quadcore.lively.api.twitter.model.TwitterStatusBuilder;
import com.quadcore.lively.api.twitter.model.TwitterUser;
import com.quadcore.lively.api.twitter.model.TwitterUserBuilder;
import com.quadcore.lively.api.twitter.util.TwitterTokenPattern;
import com.quadcore.lively.api.util.JsonUtil;
import com.quadcore.lively.api.util.TxtUtil;

import twitter4j.HashtagEntity;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.conf.Configuration;

public class CrawlDAO {
	
	private TwitterUser saveUser(User u) {
		
		TwitterUser myU = new TwitterUserBuilder()
				.setId(u.getId())
				.setName(u.getName())
				.setScreenName(u.getScreenName())
				.setFollowersCount(u.getFollowersCount())
				.setFavoritesCount(u.getFavouritesCount())
				.setUrl(u.getURL())
				.setProfileUrl(u.getProfileImageURLHttps() == null? null: u.getProfileImageURLHttps())
				.setBiggerProfileUrl(u.getBiggerProfileImageURLHttps() == null? null: u.getBiggerProfileImageURLHttps())
				.setGeoLocation(
						u.getLocation() == null ?
						new TwitterGeoLocBuilder().setEnabled(false).build()
						: new TwitterGeoLocBuilder().setEnabled(true).setLocation(u.getLocation()).build()
				)
				.setVerified(u.isVerified())
				.setEmail(u.getEmail() == null? null : u.getEmail())
				.setCreatedAt(u.getCreatedAt().getTime())
				.build();
		return myU;
	}
	
	private TwitterStatus saveStatus(Status s) {
		TwitterStatus myS = null;
		
		List<String> hashtags = new ArrayList<>(1);
		Map<String, String> mentions = new HashMap<>(1);
		
		for (HashtagEntity h: s.getHashtagEntities()) {
			hashtags.add(h.getText());
		}
		
		for (UserMentionEntity m : s.getUserMentionEntities()) {
			mentions.put(m.getScreenName(), m.getName()); //mentions 추가
		}
		
		try {
			// 이 단계에서 필터하지 않는 것은, JSON 파일에 원본으로 저장하기 위함.
			String text = (s.getRetweetedStatus() == null ? s.getText() : s.getRetweetedStatus().getText());
			
			// 이모티콘과 https 빠진 text와, wordcounts 구하기
			TxtUtil txt = new TxtUtil();
			Map<String, Long> wordcounts = new HashMap<>(1);
			StringTokenizer st = new StringTokenizer(text);
			while (st.hasMoreTokens()) {
				String raw = st.nextToken();
				
				String filtered = TwitterTokenPattern.pattern().matcher(raw).replaceAll(" ").toLowerCase();
				String stopwords = txt.read("./resources/", "english_filter");
		        String[] words = filtered.split(" |\n|\t|\\W+");
		        
		        for (String word : words) {
					if (!stopwords.contains(word.toLowerCase()) && !word.toLowerCase().equals("")) {
						if (wordcounts.containsKey(word)) {
							wordcounts.put(word, wordcounts.get(word)+1);
						} else {
							wordcounts.put(word, 1l);
						}
					}
				}
			}
			
			myS = new TwitterStatusBuilder()
					.setCreatedAt(s.getCreatedAt().getTime())
					.setScreenName(s.getUser().getScreenName())
					.setStatusId(s.getId())
					.setLanguage(s.getLang())
					.setText(
						s.getRetweetedStatus() == null ? s.getText() : s.getRetweetedStatus().getText()	
					)
					.setWordcounts(wordcounts)
					.setLength(
						s.getRetweetedStatus() == null ? s.getText().length() : s.getRetweetedStatus().getText().length()
					)
					.setBytes(
						s.getRetweetedStatus() == null ? s.getText().getBytes("utf-8").length : s.getRetweetedStatus().getText().getBytes("utf-8").length
					)
					.setHashtags(hashtags.size() > 0? hashtags : null)
					.setMentions(mentions.size() > 0? mentions : null)
					.setWordcounts(wordcounts)
					.setGeoLocation(
						s.getGeoLocation() == null?
						new TwitterGeoLocBuilder().setEnabled(false)
							.setLatitude(0.0)
							.setLongitude(0.0)
							.build()
						: new TwitterGeoLocBuilder().setEnabled(true)
							.setLatitude(s.getGeoLocation().getLatitude())
							.setLongitude(s.getGeoLocation().getLongitude())
							.build()
					)
					.setFavoriteCount(s.getFavoriteCount())
					.build();
		} catch (UnsupportedEncodingException e) {
			System.out.println("지원하지 않는 인코딩 문자체계입니다.");
			e.printStackTrace();
		}
		
		return myS;
		
	}
	

	public void target(Twitter twitter, String[] targets) {
		
		for (String target : targets) {
			List<TwitterStatus> myTS = new ArrayList<>(1);
			
			// 검색 준비
			System.out.println(target + "의 정보를 가져옵니다.");
			Paging p = new Paging();
			p.setCount(200); // 최대 출력 갯수 (200개)
			
			try {
				ResponseList<Status> res = twitter.getUserTimeline(target, p);
				if (res != null && res.size() > 0) {
					// 검색 시작 (언어 상관없음)
					JsonUtil json = new JsonUtil();
					TxtUtil txt = new TxtUtil();
					for (Status s : res) {
						if (s.getLang().equals("en")) { // 영문 트윗 사용자만 추출.
							TwitterUser myU = saveUser(s.getUser());
							json.createUserInfo(myU);
							
							TwitterStatus myS = saveStatus(s);
							myTS.add(myS);
						}
					}
					// 추출 시작
					json.createData(myTS);
					// 필터링
					txt.createDataFiltered(myTS);
				}
				
			} catch (TwitterException e) {
				int ec = e.getErrorCode();
				int sc = e.getStatusCode();
				System.out.println(ec + " 에러코드 , 상태코드 " + sc);
				continue;
			}
			
			// 종료
		    Runtime.getRuntime().addShutdownHook(new Thread("TargetCrawlShutdown") {
		    	@Override
		    	public void run() {
		    		// 남겨둬서 정상적으로 종료할 수 있도록 한다.
		    	}
		    });
			
		}
	}

	
	
	
	public void random(Configuration config) {
		JsonUtil json = new JsonUtil();
		TxtUtil txt = new TxtUtil();
		
	    // 랜덤 지정
		StatusListener listener = new StatusListener(){
	        public void onStatus(Status s) {
	        	
	        	if (s != null) {
	        		// 유저정보 저장
	        		User u = s.getUser();
	        		
	        		TwitterUser myU = saveUser(u);
	        		TwitterStatus myS = saveStatus(s);
	        		
	        		// 추출 시작 (유저정보 json + 원문 json)
	        		json.createUserInfo(myU);
	        		json.createData(myS);
	        		
	        		// 필터링
	        		txt.createDataFiltered(myS);
				}
	        	
	        }
	        
	        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
	        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
	        public void onException(Exception ex) {
	            ex.printStackTrace();
	        }
			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub
			}
	    };
	    
	    // 추출 시작
	    TwitterStream twitterStream = new TwitterStreamFactory(config).getInstance();
	    twitterStream.addListener(listener);
	    twitterStream.sample("en");
		
		
		// 종료
	    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Twitter 크롤링을 종료합니다...");
				twitterStream.cleanUp();
				System.out.print("1/3 ... ");
				twitterStream.shutdown();
				System.out.print("2/3 ... ");
				twitterStream.removeListener(listener);
				System.out.println("3/3 ... ");
				
				// 끝
				System.out.println("프로그램을 종료합니다...");
				
			}
		}, "RandomCrawlShutdown"));
	}


}
