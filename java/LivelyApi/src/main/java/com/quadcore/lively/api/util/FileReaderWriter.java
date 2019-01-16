package com.quadcore.lively.api.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileReaderWriter {
	
	private File[] selectAllFilesFromDirectory(String directory) {
		return new File(directory).listFiles();
	}
	
	private String[] loadAllFileNamesFromDirectory(String directory) {
		File[] files = selectAllFilesFromDirectory(directory);
		
		String[] fileNames = new String[files.length];
		
		for (int i = 0; i < files.length; i++) {
			fileNames[i] = files[i].getAbsolutePath();
		}
		
		return fileNames;
	}
	
	/******************************************************/
	
	/**
	 * 파일의 모든 줄 불러오기 (String 원형으로 반환)
	 * 
	 * @param filePath
	 * @return
	 */
	public String readAllToString(String filePath) {
		
		StringBuilder sb = new StringBuilder();
		
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(new File(filePath)), "UTF-8")
						//FileReaderWriter.class.getClassLoader().getResourceAsStream(filePath), "UTF-8")
				);){
			
			String s = null;
			
			while ((s = br.readLine()) != null) {
				sb.append(s+"\n");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	
	public String[] loadTsvPathsFromDirectory(String directory) {
		File[] files = selectAllFilesFromDirectory(directory);
		
		ArrayList<String> results = new ArrayList<String>(1);
		
		for (File file : files) {
			if (file.getName().endsWith(".tsv")) {
				results.add(file.getAbsolutePath());
			}
		}
		
		String[] resultsArr = new String[results.size()];
		if (resultsArr.length > 0) {
			resultsArr = results.toArray(resultsArr);
		}
		
		return resultsArr;
	}
	
	
	/**
	 * 디렉토리에 있는 모든 tsv 파일들 불러오기.
	 * @param directory
	 * @return
	 */
	public String[] loadTextsFromTSVs(String directory) {
		File[] files = selectAllFilesFromDirectory(directory);
		
		ArrayList<String> results = new ArrayList<String>(1);
		StringBuffer sb;
		
		for (File file : files) {
			if (file.getName().endsWith(".tsv")) {
				System.out.println("저장된 " + file.getName() + "를 불러오고 있습니다....");
				
				sb = new StringBuffer();
				
				try (BufferedReader br = new BufferedReader(
						new InputStreamReader(
								new FileInputStream(file), "UTF-8")
								//FileReaderWriter.class.getClassLoader().getResourceAsStream(filePath), "UTF-8")
						);){
					
					String s = null;
					
					while ((s = br.readLine()) != null) {
						sb.append(s);
						results.add(sb.toString());
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		String[] resultsArr = new String[results.size()];
		if (resultsArr.length > 0) {
			resultsArr = results.toArray(resultsArr);
		}
		
		return resultsArr;
	}
	
	/**
	 * 
	 * @param filePathAndName
	 * @param extensions
	 * @param befores
	 * @param afters
	 * @return
	 */
	public int saveToFile(String filePathAndName, String extensions, String[] befores, String[] afters) {
		int result = 0;
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePathAndName + "." + extensions, true));) {
			
			for (String text : afters) {
				bw.write(text+"\n");
				result += 1;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}


	public int saveAllSentencesToFile(String directory, String name, String extensions, String[] translatedTexts) {
		int result = 0;
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(directory + name + "." + extensions, true));) {
			
			for (String text : translatedTexts) {
				bw.write(text+"\n");
				result += 1;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

}
