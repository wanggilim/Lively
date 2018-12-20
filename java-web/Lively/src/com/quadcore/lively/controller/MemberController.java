package com.quadcore.lively.controller;

import java.sql.Date;
import java.util.List;

import com.quadcore.lively.model.MemberVO;
import com.quadcore.lively.service.MemberService;




public class MemberController {


		private MemberService service;

		public MemberController() {
			service = new  MemberService();
		}
		// TODO Auto-generated method stub
		//로그인 승인
		public int getUserAuth(String userMail, String userPass) {
			return service.getUserAuth(userMail, userPass);
}
		//가입시 기존 유뮤 확인
		public int getUserMail(String userMail) {
			return service.getUserMail(userMail);
		}

		//멤버 삭제
		public int deleteUserFromUserMail(String userMail) {
			return service.deleteUserFromUserMail(userMail);

		}
		//멤버 수정
		public Object updateUserFromUserMail(String userMail) {
			return service.updateUserFromUserMail(userMail);

		}
		//멤버 조회
		public Object searchUserFromUserMail(String userMail) {
			return service.searchUserFromUserMail(userMail);

		}
		public int registerCheck(String userMail) {
			// TODO Auto-generated method stub
			return service.registerCheck(userMail);
		}

		/**
		 * 회원 가입
		 * @Date 2018.12.15
		 * @author wgl
		 * @param member::MemberVO 가입한 멤버의 정보
		 */
		public void signUp(MemberVO member) {
			service.signUp(member);

		}

		// 세션에 저장할 MemberVO 객체
		public MemberVO getUserInfo(String userMail) {
			// TODO Auto-generated method stub
			return service.getUserInfo(userMail);
		}

		public List<MemberVO> selectByLevelMail(int userLevel, String userMail) {

			return service.selectByLevelMail(userLevel,userMail);
		}
		public void updateByMemberNo(MemberVO member, int setMemberLevel) {

			service.updateByMemberNo(member, setMemberLevel);
		}
		// 관리자 등록
		public void insertAdmin(MemberVO member) {
			service.insertAdmin(member);

		}
		//나의 정보 수정
		public Object updateMyInfo(MemberVO member,String setUserPass, String setUserGender, Date setUserBirthday) {

			return service.updateMyInfo(member,setUserPass,setUserGender,setUserBirthday);
		}

}
