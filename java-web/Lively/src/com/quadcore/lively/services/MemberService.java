package com.quadcore.lively.services;

import com.quadcore.lively.dao.MemberDAO;

public class MemberService {
	private MemberDAO dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}

	public int getUserAuth(String userMail, String userPass) {
		
		System.out.println("UserService userMail : " + userMail);
		System.out.println("UserService userPass : " + userPass);
		
		return dao.getUserAuth(userMail, userPass);
	}


}
