package com.quadcore.lively.api.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.quadcore.lively.api.twitter.model.TwitterStatus;
import com.quadcore.lively.api.twitter.model.TwitterUser;
import com.quadcore.lively.api.twitter.util.TwitterTokenPattern;

public class TxtUtil {

	public void createUserInfo(TwitterUser myU) {
		/**
		 * 개발중
		 */
		if (myU != null) {
			
		}
	}

	public void createData(List<TwitterStatus> myTS) {
		/**
		 * 개발중
		 */
		if (myTS != null && myTS.size() > 0) {
			Iterator<TwitterStatus> it = myTS.iterator();
			
			while (it.hasNext()) {
				TwitterStatus status = (TwitterStatus) it.next();
			}
		}
	}
	
	public void createDataFiltered(List<TwitterStatus> myTS) {
		for (TwitterStatus s : myTS) {
			createDataFiltered(s);
		}
	}

	public void createDataFiltered(TwitterStatus s) {
		
		// if (SNSData instanceof TwitterStatus) {
		if (s != null) {
			Matcher matcher = TwitterTokenPattern.pattern().matcher(s.getText());
			write("./data/txt/" + s.getScreenName() + "/",
					s.getStatusId() + ".txt",
					matcher.replaceAll(" ")
			);
		}
	}
	
	public String read(String filePath, String fileName) {
		StringBuffer sb = new StringBuffer();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(new File(filePath + fileName)), "UTF-8")
						//FileReaderWriter.class.getClassLoader().getResourceAsStream(filePath), "UTF-8")
				)){
			String s = null;
			while ((s = br.readLine()) != null) {
				sb.append(s+"\n");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("우선, 디렉토리에 대한 파일 읽기 권한이 있는지 확인해주세요.");
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public void write(String filePath, String fileName, String txtString) {
		File dir = new File(filePath);
		if (dir.mkdirs()) {
			System.out.println(dir.getName() + " 디렉토리를 생성합니다.");
		}
		
		if (dir.exists()) {
			File file = new File(dir, fileName);
			try {
				if (file.createNewFile()) {
					System.out.println(file.getName() + " 파일이 생성됩니다...");
					
					try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + fileName))) {
						writer.write(txtString);
						writer.flush();
						System.out.println(txtString);
						System.out.println("저장완료 ...\n");
						
					} catch (FileNotFoundException e) {
						System.out.println("파일이 존재하지 않습니다.");
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						System.out.println("인코딩 문자체계를 지원하지 않습니다.");
						e.printStackTrace();
					}

				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
