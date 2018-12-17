package com.quadcore.lively.service;

import com.quadcore.lively.model.AdminDAO;

public class AdminService {
	
	private AdminDAO dao;
	public AdminService() {
		dao = new AdminDAO();
	}
	//테이블조회
	public Object selectByTableName(String tableName) {
		String sql ="select * from user_tables where Table_name="+tableName;
		return dao.st_execute(sql);
	}
	//admin조회
	public Object selectByAdminNo(int adminNo) {
		String sql ="select * from admin where adminNo ="+adminNo;
		return dao.st_execute(sql);
	}
	//admin수정
	public Object updateByAdminNo(int adminNo) {
		return dao.st_execute("");
	}
	//admin삭제
	public Object deleteByAdminNo(int adminNo) {
		String sql ="delete from admin where AdminNo="+adminNo;
		return dao.st_execute(sql);
	}

}
