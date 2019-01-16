package com.quadcore.lively.api.main;

import java.io.IOException;

import com.quadcore.lively.api.controller.LivelyApiController;
import com.quadcore.lively.api.twitter.main.TwitterApiMain;

public class LivelyApiMain {

	private static LivelyApiMenu menu;

	public static void main(String[] args) {
		menu = new LivelyApiMenu();

		int swValue;
		String strValue;

		System.out.println(menu.showMainMenu());
		swValue = Keyin.inInt("> ");
		
		switch (swValue) {
		case 1:
			menu.clrscr();
			System.out.println(menu.twitterInputMessage());
			strValue = Keyin.inString("> ");
			
			String[] inputs = strValue.split(" ");
			// 프로그램 종료
			if (inputs[0].equals("0")) {
				break;
			}
			
			String screenName = inputs[0];
			String papagoId = inputs[1];
			String papagoPass = inputs[2];
			
			
			
			
			LivelyApiController controller = new LivelyApiController();
			// 수집
			System.out.println(menu.twitterCrawlMessage());
			controller.crawl(screenName);
			
			// 번역 + 저장
			System.out.println(menu.twitterTSMessage());
			controller.translateAndSave(papagoId, papagoPass, "./LivelyApiLogs/", screenName, "txt");
			break;
		case 2:
			menu.clrscr();
			break;
		default:
			menu.clrscr();
			System.out.println("잘못 입력했습니다.");
			break;
		}
		
		System.out.println("프로그램을 종료합니다.");
	}
}

class Keyin {

	// 프롬프트 출력
	public static void printPrompt(String prompt) {
		System.out.print(prompt + " ");
		System.out.flush();
	}

	// 입력 스트림에 데이터를 입력할 수 없을 때
	public static void inputFlush() {
		int dummy;
		int bAvail;

		try {
			while ((System.in.available()) != 0)
				dummy = System.in.read();
		} catch (java.io.IOException e) {
			System.out.println("Input error");
		}
	}

	public static String inString() {
		int aChar;
		String s = "";
		boolean finished = false;

		while (!finished) {
			try {
				aChar = System.in.read();
				if (aChar < 0 || (char) aChar == '\n')
					finished = true;
				else if ((char) aChar != '\r')
					s = s + (char) aChar; // Enter into string
			}

			catch (java.io.IOException e) {
				System.out.println("입력 오류 발생");
				finished = true;
			}
		}
		return s;
	}
	
	public static String inString(String prompt) {
		while (true) {
			inputFlush();
			printPrompt(prompt);
			return inString().trim();
		}
	}

	public static int inInt(String prompt) {
		while (true) {
			inputFlush();
			printPrompt(prompt);
			try {
				return Integer.valueOf(inString().trim()).intValue();
			}

			catch (NumberFormatException e) {
				System.out.println("숫자만 입력이 가능합니다!");
			}
		}
	}
	
	
}