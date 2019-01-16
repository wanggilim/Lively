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
		.append("1) 트위터 데이터 수집 + 파파고 해석 시작\n"
//				+ "예제 8계정\n" + 
//				"(버락 오바마, 트럼프, 힐러리 클린턴,\n" + 
//				"BBC, CNN, 아리랑, 코리아헤럴드, UN)\n")
				+ "예제 1계정 (트럼프)\n")
		.append("2) 종료하기\n")
		.append("========================");
		
		return sb.toString();
	}
	
	public String twitterCrawlMessage() {
		return "트위터 문장 수집을 시작합니다.";
	}
	
	public String twitterTSMessage() {
		return "수집한 문장을 번역하고 통합 저장합니다.\n"
				+ "(Translate by Papago/NAVER)\n"
				+ "(* 일일 번역 가능량이 있으므로, 실제는 적게 저장될 수 있습니다.)";
	}
	
}
