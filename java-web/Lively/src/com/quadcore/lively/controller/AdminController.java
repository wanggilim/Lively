package com.quadcore.lively.controller;

import com.quadcore.lively.service.AdminService;
import com.quadcore.lively.service.MemberService;

public class AdminController {
	
	private AdminService service;
	
	public AdminController() {
		service = new  AdminService();
	}
	//테이블조회
	public Object selectByTableName(String tableName) {
		return service.selectByTableName(tableName);
		
	}
	//admin조회
	public Object selectByUserNo(int adminNo) {
		return service.selectByAdminNo(adminNo);
		
	}
	//admin수정
	public Object updateByUserNo(int adminNo) {
		return service.selectByAdminNo(adminNo);
	}
	//admin삭제
	public Object deleteByAdminNo(int adminNo) {
		return service.deleteByAdminNo(adminNo);
		
	}
	

}
