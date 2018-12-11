package com.quadcore.lively.controller;

import com.quadcore.lively.model.MemberService;

public class MemberController {

	
	public int getUserAuth(String userId, String userPass) {
		
		return MemberService.getUserAuth(userId,userPass);
	}

}
