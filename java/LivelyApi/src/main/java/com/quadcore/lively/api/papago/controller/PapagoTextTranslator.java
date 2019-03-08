package com.quadcore.lively.api.papago.controller;

import java.io.IOException;

public class PapagoTextTranslator {

//	private PapagoConnection conn;
//	
//	public PapagoTextTranslator(String papagoId, String papagoPass) {
//		conn = new PapagoConnection(papagoId, papagoPass);
//	}
//	
//	public String[] translateEK(String[] englishTexts) throws IOException {
//		String[] notParsed = conn.getJson(englishTexts); // Json 형태로 해석
//		
//		String[] npEnglish = new String[notParsed.length];
//		String[] npKorean = new String[notParsed.length];
//		for (int i = 0; i < notParsed.length; i++) {
//			npEnglish[i] = notParsed[i].split("\t")[0];
//			npKorean[i] = notParsed[i].split("\t")[1];
//		}
//		
//		PapagoJsonParser parser = new PapagoJsonParser();
//		String[] parsedKorean = parser.jsonsToStrings(npKorean); // 우리말만 해석 (Json 파싱 완료)
//		
//		// 영문 \t 번역 형태로 전달
//		// ex) 'English Conversation \t 영어 회화'
//		String[] results = new String[parsedKorean.length];
//		for (int i = 0; i < results.length; i++) {
//			results[i] = npEnglish[i] + "\t" + parsedKorean[i];
//		}
//		
//		return results;
//		
//	}
}
