package com.quadcore.lively.api.twitter.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.quadcore.lively.api.twitter.model.TwitterStatus;
import com.quadcore.lively.api.twitter.util.FileReaderWriter;

public class FileDAO {
	private FileReaderWriter fileReaderWriter;
	
	public FileDAO() {
		fileReaderWriter = new FileReaderWriter();
	}
	
	/**
	 * 파일 읽고 라인이 중복되면 파일 그만 삽입
	 * @author wgl
	 * @Date 2018.12.19
	 * @param status
	 * @return
	 */
	public int insertStatementIntoFile(List<TwitterStatus> statuses) {
		int result = 0;
		
		String screenName = statuses.get(0).getScreenName();
		
		try {
			Path path = Paths.get("./LivelyApiLogs/");
			Files.createDirectory(path);
		} catch (IOException e) {
			System.out.println("LivelyApiLogs 디렉토리로 저장합니다.");
		}
		
		File file = new File("./LivelyApiLogs/" + "@" + screenName + ".tsv");
		
		duplicateCheck:for (int i = 0; i < statuses.size(); i++) {
			
			// 파일 존재여부 확인 기능, 파일 내용 중복하지 않는다면 파일에 해당 줄 삽입
			try {
				
				if (file.createNewFile()) {
					// 파일 생성시
					System.out.println(file.getName() + " 파일을 생성합니다!");
					
					for (TwitterStatus status : statuses) {
						fileReaderWriter.write(file.getPath(), status.getStmt());
						result += 1;
					}
					break duplicateCheck;
					
				} else {
					// 파일 존재시 -> 중복되지 않는 내용만 파일에 추가
					System.out.println(file.getName() + " 파일이 존재합니다...");
					TwitterStatus status = statuses.get(i);
					String inputLine = status.getStmt() + "\n";
					
					List<String> list = fileReaderWriter.readAllToList(file.getPath());
					Collections.reverse(list);
					
					if (list.get(0).equals(inputLine)) {
						break duplicateCheck;
					}
					
					fileReaderWriter.write(file.getPath(), status.getStmt());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return result;
	}

	/**
	 * 파일 읽고 DB에 넣을 라인들만 뽑아오기
	 * @author wgl
	 * @Date 2018.12.19
	 * @param status
	 * @return
	 */
	public List<TwitterStatus> getNotDuplicatedStatements(List<TwitterStatus> statuses) {
		List<TwitterStatus> notDuplicatedStatements = new ArrayList<>(1);
		
		String screenName = statuses.get(0).getScreenName();
		
		File file = new File("raw/twitter/" + "@" + screenName + ".tsv");
		
		duplicateCheck:for (int i = 0; i < statuses.size(); i++) {
			
			// 파일 존재여부 확인 기능, 파일 내용 중복하지 않는다면 파일에 해당 줄 삽입
			if (! file.exists()) {
				// 파일 미존재시
				System.out.println(file.getName() + " 파일이 존재하지 않습니다.");
				break duplicateCheck;
				
			} else {
				// 파일 존재시 -> 중복되지 않는 내용만 리스트로 따로 추가하기
				TwitterStatus status = statuses.get(i);
				String inputLine = status.getStmt() + "\n";
				
				List<String> list = fileReaderWriter.readAllToList(file.getPath());
				Collections.reverse(list);
				
				if (list.get(0).equals(inputLine)) {
					break duplicateCheck;
				}
				
				notDuplicatedStatements.add(status);
			}
		}
			
		return notDuplicatedStatements;
	}

}
