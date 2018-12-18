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
	//admin&member 조건 조회
	public Object selectByLevelEmail(int level, String mail, String tableName) {
		return service.selectByLevelEmail(level, mail,tableName);
	}
	
	//admin수정
	public Object updateByUserNo(int adminNo) {
		return service.selectByAdminNo(adminNo);
	}
	
	//admin삭제
	public Object deleteByAdminNo(int adminNo) {
		return service.deleteByAdminNo(adminNo);
	}
	
	//admin 등록
	public int insertAdmin(String adminEmail,String adminPass) {
		return service.insertAdmin(adminEmail,adminPass);
	}

	public Object updateByAdminNo(int adminNo, String adminEmail, String adminPass, int adminLevel, int setAdminLevel) {
		return service.updateAdmin(adminNo, adminEmail,adminPass, adminLevel, setAdminLevel);
	}

	public Object updateByMemberNo(int userNo, String userMail, String userPass, int userLevel, String gender, String birthday,int setMemberLevel) {
		 return service.updateByMemberNo(userNo,userMail,userPass,userLevel,gender,birthday,setMemberLevel);
		
	}
	

}
