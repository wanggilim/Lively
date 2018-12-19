package com.quadcore.lively.twitter.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderWriter {
	
	public FileReaderWriter() {
		
	}
	
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
						//new FileInputStream(new File(filePath)), encodingType)
						FileReaderWriter.class.getClassLoader().getResourceAsStream(filePath), "UTF-8")
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
	
	/**
	 * 파일의 모든 줄 불러오기 (ArrayList 로 반환)
	 * 
	 * @param filePath
	 * @return
	 */
	public List<String> readAllToList(String filePath) {
		List<String> result = new ArrayList<>(1);
		
		StringBuilder sb = new StringBuilder();
		
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(
					new FileInputStream(new File(filePath)), "UTF-8")
				);){
			
			String s = null;
			
			while ((s = br.readLine()) != null) {
				result.add(sb.append(s).append("\n").toString());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public String readLatestLine(String filePath) {
		String result = "";
		
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(new File(filePath)), "UTF-8")
				);){
			
			String s = null;
			
			while ((s = br.readLine()) != null) {
				result = s;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * append (filePath 에 있는 파일로 contents를 넣기)
	 * @param filePath
	 * @param contents
	 * @return
	 */
	public int write(String filePath, String content) {
		int result = 0;
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));) {
			bw.write(content);
			bw.close();
			result += 1;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
