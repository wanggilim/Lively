package com.quadcore.lively.api.twitter.main;

import java.util.ArrayList;
import java.util.List;

import com.quadcore.lively.api.twitter.controller.TwitterStatusController;
import com.quadcore.lively.api.twitter.model.TwitterStatus;
import com.quadcore.lively.api.twitter.util.TwitterHelper;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterApiMain {
	
	public TwitterApiMain(String screenName) {
		main(new String[] {screenName});
	}
	
	public static void main(String[] args) {
		Twitter twitter = new TwitterHelper().getTwitter();
		
		
//		String[] namesToSearch = new String[] {
//				"itsDakotaFannin", "danedehaan", "VancityReynolds", "LeoDiCaprio",
//				"RobertDowneyJr", "RWitherspoon", "selenagomez", "justinbieber",
//				"Camila_Cabello", "ArianaGrande", "ddlovato", "rihanna", "ladygaga",
//				"Pontifex", "BarackObama", "realDonaldTrump", "TomCruise",
//				"BBCBreaking", "CNN", "arirangworld", "TheKoreaHerald",
//				"HillaryClinton", "UN", "BillGates",  "teamcoco",
//				"billyjoel", "therock", "vindiesel"
//		}; // 검색할 트윗 스크린네임/계정명
		
		String[] namesToSearch = new String[] {
				args[0]
		}; // 검색할 트윗 스크린네임/계정명
		
		
		
		
		for (String nameToSearch : namesToSearch) {
			System.out.println(nameToSearch + "의 정보를 가져옵니다.");
			List<TwitterStatus> statuses = new ArrayList<>(1); // TwitterStatus 로 바꾼 Status(Twitter) 리스트
			try {
				Paging p = new Paging();
				p.setCount(200); // 최대 출력 갯수 (200개)
				
				ResponseList<Status> res = twitter.getUserTimeline(nameToSearch, p); // 타임라인 중 가장 최근 내용들
				
				/**
				 * 계정이 있고, 내용이 있다면
				 */
				
				if (res != null) {
					for (Status statusFromTwitter : res) {
						
						String screenName = "";
						String name = "";
						String stmt = "";
						long stmtTs = 0l;
						String stmtURL = "";
						String location = "";
						String userProfileURL = "";
						String language = "";
						// (1) Status From Twitter
						screenName = statusFromTwitter.getUser().getScreenName();
						name = statusFromTwitter.getUser().getName();
						stmt = (statusFromTwitter.getRetweetedStatus() == null ? statusFromTwitter.getText() : statusFromTwitter.getRetweetedStatus().getText());
						stmtTs = statusFromTwitter.getCreatedAt().getTime();
						stmtURL = "https://twitter.com/" + screenName + "/status/" + statusFromTwitter.getId();
						location = statusFromTwitter.getUser().getLocation();
						language = statusFromTwitter.getLang();
						userProfileURL = statusFromTwitter.getUser().getBiggerProfileImageURLHttps();
						
						// (2) Status -> TwitterStatus
						TwitterStatus status = new TwitterStatus(); // stmtType 은 t 로 고정
						status.setScreenName(screenName);
						status.setName(name);
						status.setStmt(stmt);
						status.setStmtTs(stmtTs);
						status.setLocation(location);
						status.setUserProfileURL(userProfileURL);
						status.setStmtURL(stmtURL);
						status.setLanguage(language);
						
						// (3) statues += TwitterStatus 
						statuses.add(status);
					}
				}
			} catch (TwitterException e) {
				// 로그파일 작성 (예정)
				System.out.println("트위터 서버에 이상이 있는지 확인해주시거나 계정을 다시 입력해주세요.");
				e.printStackTrace(); 
			}
			
			
			/**
			 * statuses 가 존재하고 1개 이상이라면
			 */
			
			if (statuses != null && statuses.size() > 0) {
				System.out.println("모든 정보를 가져왔습니다. 파일로 저장중입니다.");
				
				TwitterStatusController controller = new TwitterStatusController();
				
//				int insertCellebResult = 0; // DB의 celleb 테이블 insert 건수
				int insertFileResult = 0; 	// 생성된 파일 수
//				int insertStmtResult = 0;	// DB의 stmt 테이블 insert 건수 (추후 API 프로젝트(LivelyAPI) 통합시 이전될 예정)
				
				// (4) TwitterStatus -> DB의 celleb 테이블 (중복체크 포함)
//				insertCellebResult += controller.insertCellebTable(statuses.get(0));
				
				// (5) TwitterStatus -> 파일로 생성 (파일명 : raw/twitter/@_____.tsv) (중복체크 포함)
				insertFileResult += controller.insertStatementIntoFile(statuses);
				
				// (6) TwitterStatus -> DB의 stmt 테이블 (중복체크 포함)
				// 작업 예정
//				insertStmtResult += controller.insertFromFileToStmtTable(statuses);
				
				
				StringBuilder sb = new StringBuilder();
//				sb.append("DB 총 입력 건수").append("\n\t")
//				.append("1. celleb 테이블 : ").append(insertCellebResult).append("\n\t")
//				.append("2. stmt 테이블 : ").append(insertStmtResult).append("\n")
//				.append("File 총 입력 건수 : ").append(insertFileResult).append("\n");
				
				sb.append("File 총 저장 건수 : ").append(insertFileResult).append("\n");
				System.out.println(sb.toString());
			}
			
			
		} // end of FOR statement
		
			
	} // end of main

}
