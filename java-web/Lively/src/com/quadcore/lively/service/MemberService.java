package com.quadcore.lively.service;



import java.sql.Date;
import java.util.List;

import com.quadcore.lively.model.MemberDAO;
import com.quadcore.lively.model.MemberVO;

public class MemberService{
	
	private MemberDAO dao;
	
	public MemberService() {
		dao = new MemberDAO();
		
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
	public void deleteMember(int userNo) {
		// TODO Auto-generated method stub
		dao.delete(userNo);
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

	public Object deleteUserFromUserMail(int userNo) {
		String sql ="delete from member where userNo="+userNo;
		return dao.st_execute(sql);
	}

	public Object updateUserFromUserMail(String userMail) {
		return dao.st_execute("");
	}

	public Object searchUserFromUserMail(String userMail) {
		String sql ="select * from member where usermail="+userMail;
		return dao.st_execute(sql);
	}
	public int registerCheck(String userMail) {
		// TODO Auto-generated method stub
		return dao.getUserMail(userMail);
	}
	//멤버레벌 얻기

	public MemberVO selectByUserLevel(int userNo) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<MemberVO> selectByLevelMail(int userLevel, String userMail) {
		// TODO Auto-generated method stub
		return dao.selectByUserLevelMail(userLevel,userMail);
	}

	public void updateByMemberNo(MemberVO member, int setMemberLevel) {
		dao.updateByMemberNo(member,setMemberLevel);
		
	}
	//관리자 등록
	public void insertAdmin(MemberVO member) {
		// TODO Auto-generated method stub
		dao.insertAdmin(member);
	}
	//나의 정보 수정
	public Object updateMyInfo(MemberVO member,String setUserPass, String setUserGender, Date setUserBirthday) {
		
		return dao.updateMyInfo(member ,setUserPass,setUserGender,setUserBirthday);
		
	}




}
