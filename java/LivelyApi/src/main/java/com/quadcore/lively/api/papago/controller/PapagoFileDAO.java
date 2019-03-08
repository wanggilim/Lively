package com.quadcore.lively.api.papago.controller;

import com.quadcore.lively.api.util.FileReaderWriter;

public class PapagoFileDAO {
	
	public int saveAllSentencesToFile(String directory, String name, String extensions, String[] translatedTexts) {
		FileReaderWriter writer = new FileReaderWriter();
		return writer.saveAllSentencesToFile(directory, name, extensions, translatedTexts);
	}

	public String readAllToString(String filePath) {
		FileReaderWriter readerWriter = new FileReaderWriter();
		return readerWriter.readAllToString(filePath);
	}
	
	public String[] loadTextsFromTSVs(String directory) {
		FileReaderWriter reader = new FileReaderWriter();
		return reader.loadTextsFromTSVs(directory);
	}
	
	public String[] loadTsvPathsFromDirectory(String directory) {
		FileReaderWriter reader = new FileReaderWriter();
		return reader.loadTsvPathsFromDirectory(directory);
	}

}
