package com.quadcore.lively.api.twitter.service;

import com.quadcore.lively.api.twitter.dao.CellebDAO;
import com.quadcore.lively.api.twitter.model.TwitterStatus;

public class CellebService {
	private CellebDAO dao;
	
	public CellebService() {
		dao = new CellebDAO();
	}

	public int insertCellebTable(TwitterStatus status) {
		String sql = "insert into celleb(cellebno, cellebname, accounts) values(cellebno.NEXTVAL, ?, ?)";
		
		return dao.doDML(sql, status);
	}

	public int getCellebNoByName(TwitterStatus status) {
		String name = status.getName();
		String sql = "select cellebno from celleb where cellebname = '" + name + "'";
		return dao.getCellebNoByName(sql);
	}

	public String getCellebNameByNo(int cellebNo) {
		String sql = "select cellebname from celleb where cellebno = " + cellebNo;
		return dao.getCellebNameByNo(sql);
	}
	
	
	

}
