package com.quadcore.lively.service;


import com.quadcore.lively.model.JoinDao;
import com.quadcore.lively.model.MemberVO;

public class JoinService{
	
	private JoinDao dao;
	
	public JoinService() {
		dao = new JoinDao();
		
	}

	//멤버추가
	public void join(MemberVO m) {
		// TODO Auto-generated method stub
		dao.insert(m);
	}

	//멤버검색
	public MemberVO getMember(String userMail) {
		// TODO Auto-generated method stub
		return dao.select(userMail);
	}

	//멤버수정
	public void editMember(MemberVO m) {
		// TODO Auto-generated method stub
		dao.update(m);
	}

	//멤버삭제
	public void deleteMember(String userMail) {
		// TODO Auto-generated method stub
		dao.delete(userMail);
	}

	public int getUserAuth(String userMail, String userPass) {
		// TODO Auto-generated method stub
		return dao.getUserAuth(userMail, userPass);
	}

	public int getUserMail(String userMail) {
		// TODO Auto-generated method stub
		return dao.getUserMail(userMail);
	}



}
