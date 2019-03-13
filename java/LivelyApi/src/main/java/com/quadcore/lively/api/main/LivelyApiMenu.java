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
		.append("1) 트위터 데이터 수집\n")
		//.append("2) 파파고 해석 시작\n")
		.append("2) 종료하기\n")
		.append("========================");
		
		return sb.toString();
	}
	
	// 공통 //////////////////////////////
	public String wrongInputMessage() {
		return "잘못 입력했습니다.";
	}
	
	public String exitMessage() {
		return "프로그램을 종료합니다.";
	}
	
	
	
	/**
	 * 
	 * 트위터
	 * 
	 */
	
	// 트위터 : 반복 추출 //////////////////////////////
	public String twitterMenuMessage() {
		sb = new StringBuffer();
		sb.append("========================\n")
		.append("트위터 데이터 수집\n")
		.append("1) 선택된 트위터 사용자/셀럽만 반복 추출\n")
		.append("2) 트윗 공개 메시지 무작위 추출\n")
		.append("0) 종료하기\n")
		.append("========================");
		return sb.toString();
	}
	
	// 트위터 : 반복 추출 //////////////////////////////
	public String twitterTargetMessage() {
		sb = new StringBuffer();
		sb.append("지정된 대상들에 대한 트위터 정보들을 불러와 대상을 확인 추출하고 있습니다.");
		return sb.toString();
	}
	
	// 트위터 : 무작위 추출 //////////////////////////////
	public String twitterRandomMessage() {
		sb = new StringBuffer();
		sb.append("무작위의 트윗을 추출하고 있습니다.");
		return sb.toString();
	}
	
	
	/**
	 * 
	 * 파파고
	 * 
	 */
	
	// 파파고 : 지정 추출 + 번역 //////////////////////////////
	public String twitterWithPapago() {
		sb = new StringBuffer();
		
		sb.append("\n")
		.append("트위터 스크린네임(대소문자 구분)과 파파고 클라이언트 아이디와 비밀번호를 입력해주세요.\n")
		.append("*** ex) realDonaldTrump I1D2 P3W4\n")
		.append("0) 종료하기\n");
		
		return sb.toString();
	}
	
	public String twitterStartCrawl() {
		return "\n트위터 문장 수집을 시작합니다.";
	}
	
	public String twitterTranslateMessage() {
		return "수집한 문장을 번역하고 통합 저장합니다.\n"
				+ "(Translate by Papago/NAVER)\n"
				+ "(* 일일 번역 가능량이 있으므로, 실제는 적게 저장될 수 있습니다.)";
	}
	
}
