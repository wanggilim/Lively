package com.quadcore.lively.controller;

import com.quadcore.lively.model.MemberService;

public class MemberController {
	private MemberService service;
	
	public MemberController() {
		service = new MemberService();
	}
	
	public int getUserAuth(String userId, String userPass) {	
		
		return service.getUserAuth(userId,userPass);
	}

}
