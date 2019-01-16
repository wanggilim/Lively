package com.quadcore.lively.api.main;

import java.io.IOException;

public class LivelyApiMenu {
	
	private StringBuffer sb;
	
	public void clrscr(){
	    //Clears Screen in java
	    try {
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {}
	}
	
	public String showMainMenu() {
		sb = new StringBuffer();
		
		sb.append("========================\n")
		.append("Lively Api\n")
		.append("1) 트위터 데이터 수집 + 파파고 해석 시작\n")
		.append("2) 종료하기\n")
		.append("========================");
		
		return sb.toString();
	}
	
	public String twitterInputMessage() {
		sb = new StringBuffer();
		
		sb.append("\n")
		.append("트위터 스크린네임(대소문자 구분)과 파파고 클라이언트 아이디와 비밀번호를 입력해주세요.\n")
		.append("*** ex) realDonaldTrump I1D2 P3W4\n")
		.append("0) 종료하기\n");
		
		return sb.toString();
	}
	
	public String twitterCrawlMessage() {
		return "\n트위터 문장 수집을 시작합니다.";
	}
	
	public String twitterTSMessage() {
		return "수집한 문장을 번역하고 통합 저장합니다.\n"
				+ "(Translate by Papago/NAVER)\n"
				+ "(* 일일 번역 가능량이 있으므로, 실제는 적게 저장될 수 있습니다.)";
	}
	
}
