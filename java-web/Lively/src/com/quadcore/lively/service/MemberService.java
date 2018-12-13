package com.quadcore.lively.service;



import com.quadcore.lively.model.MemberDao;
import com.quadcore.lively.model.MemberVO;

public class MemberService{
	
	private MemberDao dao;
	
	public MemberService() {
		dao = new MemberDao();
		
	}

	//멤버추가
	public void signUp(MemberVO member) {
		// TODO Auto-generated method stub
		dao.insert(member);
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
	
	public MemberVO getUserLevel(String userMail) {
		return dao.getUserLevel(userMail);
	}

	public Object deleteUserFromUserMail(String userMail) {
		String sql ="delete from member where usermail="+userMail;
		return dao.st_execute(sql);
	}

	public Object updateUserFromUserMail(String userMail) {
		return dao.st_execute("");
	}

	public Object searchUserFromUserMail(String userMail) {
		String sql ="select * from member where usermail="+userMail;
		return dao.st_execute(sql);
	}



}
