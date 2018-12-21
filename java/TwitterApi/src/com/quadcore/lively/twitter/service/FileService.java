package com.quadcore.lively.twitter.service;

import java.util.ArrayList;
import java.util.List;

import com.quadcore.lively.twitter.dao.FileDAO;
import com.quadcore.lively.twitter.model.TwitterStatus;

public class FileService {
	private FileDAO dao;
	
	public FileService() {
		dao = new FileDAO();
	}
	
	private List<TwitterStatus> getFilteredList(List<TwitterStatus> statuses) {
		List<TwitterStatus> filteredList = new ArrayList<>(1);
		
		for (TwitterStatus status : statuses) {
			if (status.getLanguage().equals("en")) {
				filteredList.add(status);
			}
		}
		
		return filteredList;
	}

	public int insertStatementIntoFile(List<TwitterStatus> statuses) {
		return dao.insertStatementIntoFile(getFilteredList(statuses));
	}
	
	public List<TwitterStatus> getNotDuplicatedStatements(List<TwitterStatus> statuses) {
		return dao.getNotDuplicatedStatements(getFilteredList(statuses));
	}
	
}
