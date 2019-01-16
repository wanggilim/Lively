package com.quadcore.lively.api.twitter.controller;

import java.util.List;

import com.quadcore.lively.api.twitter.model.TwitterStatus;
import com.quadcore.lively.api.twitter.service.CellebService;
import com.quadcore.lively.api.twitter.service.FileService;
import com.quadcore.lively.api.twitter.service.StmtService;

public class TwitterStatusController {
	private FileService fileService;
	private CellebService cellebService;
	private StmtService stmtService;
	
	
	public TwitterStatusController() {
		fileService = new FileService();
		cellebService = new CellebService();
		stmtService = new StmtService();
	}

	// CellebService
	public int insertCellebTable(TwitterStatus status) {
		String name = status.getName();
		
		int cellebNo = getCellebNoByName(status);
		String compareName = getCellebNameByNo(cellebNo);
		
		if (! name.equals(compareName)) {
			return cellebService.insertCellebTable(status);
		}
		
		return 0;
	}
	
	public int getCellebNoByName(TwitterStatus status) {
		return cellebService.getCellebNoByName(status);
	}
	
	public String getCellebNameByNo(int cellebNo) {
		return cellebService.getCellebNameByNo(cellebNo);
	}

	
	// FileService
	public int insertStatementIntoFile(List<TwitterStatus> statuses) {
		return fileService.insertStatementIntoFile(statuses);
	}
	
	// StmtService
	public int insertFromFileToStmtTable(List<TwitterStatus> statuses) {
		List<TwitterStatus> notDuplicatedList = fileService.getNotDuplicatedStatements(statuses);
		int cellebNo = getCellebNoByName(statuses.get(0));
		return stmtService.insertFileToStmtTable(cellebNo, notDuplicatedList);
	}


	
	
}
