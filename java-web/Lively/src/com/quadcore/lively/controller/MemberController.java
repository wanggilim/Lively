package com.quadcore.lively.controller;

import com.quadcore.lively.services.MemberService;

public class MemberController {
	private MemberService service;
	
	public MemberController() {
		service = new MemberService();
	}
	
	public int getUserAuth(String userMail, String userPass) {
		
		
		System.out.println("Controller userMail : " + userMail);
		System.out.println("Controller userPass : " + userPass);
		
		return service.getUserAuth(userMail, userPass);
	}

}
