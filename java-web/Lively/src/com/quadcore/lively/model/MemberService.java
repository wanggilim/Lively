package com.quadcore.lively.model;

import com.quadcore.lively.dao.MemberDAO;

public class MemberService {
	private MemberDAO memberDAO;
	
	
	
	//로그인 ID PWD 확인 로직
	public MemberVO getUserAuth(String userId, String userPass) {
		
		return MemberDAO.getUserAuth(userId,userPass);
	}

}
