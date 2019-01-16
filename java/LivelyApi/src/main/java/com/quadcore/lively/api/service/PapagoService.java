package com.quadcore.lively.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.quadcore.lively.api.dao.PapagoFileDAO;
import com.quadcore.lively.api.dao.PapagoTextTranslator;
import com.quadcore.lively.api.util.FileReaderWriter;

public class PapagoService {
	
	private PapagoTextTranslator translator;
	private PapagoFileDAO fileDAO;
	
	private String[] translatedTexts;

	public PapagoService() {
		fileDAO = new PapagoFileDAO();
	}
	
	private String[] getFilteredTexts(String filePath) {
		FileReaderWriter readerWriter = new FileReaderWriter();
		fileDAO.readAllToString(filePath);
		String[] textsFromTsv = readerWriter.readAllToString(filePath).split("\n");
		
		final Pattern pattern = Pattern.compile(
	            "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
	            Pattern.UNICODE_CASE |
	            Pattern.CANON_EQ |
	            Pattern.CASE_INSENSITIVE
		);
		
		ArrayList<String> result = new ArrayList<>(1);
		
		for (String text : textsFromTsv) {
			Matcher matcher = pattern.matcher(text);
			result.add(matcher.replaceAll(""));
		}
		
		String[] resultArr = new String[result.size()];
		resultArr = result.toArray(resultArr);
		
		return resultArr;
	}

	
	
	private String[] translate(String papagoId, String papagoPass, String filePath) {
		
		try {
			translator = new PapagoTextTranslator(papagoId, papagoPass);
			translatedTexts = translator.translateEK(getFilteredTexts(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return translatedTexts;
	}
	
	
	public int saveAllSentencesToFile(String directory, String name, String extensions, String[] translatedTexts) {
		return fileDAO.saveAllSentencesToFile(directory, name, extensions, translatedTexts);
	}
	
	/*******************************************************/
	
	public int translateAndSave(
			String papagoId, String papagoPass,
			String directory, String name, String extensions) {
		FileReaderWriter readerWriter = new FileReaderWriter();
		
		String[] loadedPaths = readerWriter.loadTsvPathsFromDirectory(directory);
		String[] translatedTexts = null;
		
		int savedCount = 0;
		
		// 번역
		for (String path : loadedPaths) {
			translatedTexts = translate(papagoId, papagoPass, path);
		}
		
		// 저장
		savedCount += saveAllSentencesToFile(directory, name, extensions, translatedTexts);
		System.out.println("총 : " + savedCount + " 문장을 저장했습니다.");
		
		return savedCount;
	}
	

}
