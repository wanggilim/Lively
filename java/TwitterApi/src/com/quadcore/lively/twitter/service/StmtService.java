package com.quadcore.lively.twitter.service;

import java.util.List;

import com.quadcore.lively.twitter.dao.StmtDAO;
import com.quadcore.lively.twitter.model.TwitterStatus;

public class StmtService {
	private StmtDAO dao;
	
	public StmtService() {
		dao = new StmtDAO();
	}
	
	public int insertFileToStmtTable(int cellebNo, List<TwitterStatus> statuses) {
		String sql = "insert into stmt values(stmtno.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		return dao.doDML(sql, cellebNo, statuses);
	}

}
