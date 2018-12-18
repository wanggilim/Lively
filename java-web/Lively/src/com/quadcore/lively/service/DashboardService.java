package com.quadcore.lively.service;

import java.util.List;

import com.quadcore.lively.model.DashboardDAO;
import com.quadcore.lively.model.StmtVO;
import com.quadcore.lively.model.WordVO;

public class DashboardService {
	private DashboardDAO dao;
	
	public DashboardService() {
		dao = new DashboardDAO();
	}
	
	public List<WordVO> searchWords(String word) {
		return dao.searchWords(word);
	}

	public List<StmtVO> searchStatements(String word) {
		return dao.searchStatements(word);
	}

}
