package com.quadcore.lively.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.quadcore.lively.api.util.FileReaderWriter;

public class PapagoController {
	
	
	
//	public int translateAndSave(
//			String papagoId, String papagoPass,
//			String directory, String name, String extensions) {
//		return papago.translateAndSave(
//				papagoId, papagoPass,
//				directory, name, extensions);
//	}

	
	
//	private PapagoTextTranslator translator;
//	private PapagoFileDAO fileDAO;
//	
//	private String[] translatedTexts;
//
//	public PapagoService() {
//		fileDAO = new PapagoFileDAO();
//	}
//	
//	public PapagoConfiguration config() {
//		return new PapagoConfiguration();
//	}
//	
//	
//	private String[] getFilteredTexts(String filePath) {
//		FileReaderWriter readerWriter = new FileReaderWriter();
//		fileDAO.readAllToString(filePath);
//		String[] textsFromTsv = readerWriter.readAllToString(filePath).split("\n");
//		
//		final Pattern pattern = Pattern.compile(
//	            "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
//	            Pattern.UNICODE_CASE |
//	            Pattern.CANON_EQ |
//	            Pattern.CASE_INSENSITIVE
//		);
//		
//		ArrayList<String> result = new ArrayList<>(1);
//		
//		for (String text : textsFromTsv) {
//			Matcher matcher = pattern.matcher(text);
//			result.add(matcher.replaceAll(""));
//		}
//		
//		String[] resultArr = new String[result.size()];
//		resultArr = result.toArray(resultArr);
//		
//		return resultArr;
//	}
//
//	
//	
//	private String[] translate(String papagoId, String papagoPass, String filePath) {
//		
//		try {
//			translator = new PapagoTextTranslator(papagoId, papagoPass);
//			translatedTexts = translator.translateEK(getFilteredTexts(filePath));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return translatedTexts;
//	}
//	
//	
//	public int saveAllSentencesToFile(String directory, String name, String extensions, String[] translatedTexts) {
//		return fileDAO.saveAllSentencesToFile(directory, name, extensions, translatedTexts);
//	}
//	
//	/*******************************************************/
//	
	public int translateAndSave(
			String papagoId, String papagoPass,
			String directory, String name, String extensions) {
//		FileReaderWriter readerWriter = new FileReaderWriter();
//		
//		String[] loadedPaths = readerWriter.loadTsvPathsFromDirectory(directory);
//		String[] translatedTexts = null;
//		
//		int savedCount = 0;
//		
//		// 번역
//		for (String path : loadedPaths) {
//			translatedTexts = translate(papagoId, papagoPass, path);
//		}
//		
//		// 저장
//		savedCount += saveAllSentencesToFile(directory, name, extensions, translatedTexts);
//		System.out.println("총 : " + savedCount + " 문장을 저장했습니다.");
//		
//		return savedCount;
		
		return 0;
	}
	
}

class PapagoConfiguration {
	private static final String URL = "https://openapi.naver.com/v1/papago/n2mt";
	private static final String APP_NAME = "";
	private static final String CLIENT_ID = "";
	private static final String CLIENT_SECRET = "";
	
	
	public static String getUrl() {
		return URL;
	}

	public static String getAppName() {
		return APP_NAME;
	}

	public static String getClientId() {
		return CLIENT_ID;
	}

	public static String getClientSecret() {
		return CLIENT_SECRET;
	}
	
}
