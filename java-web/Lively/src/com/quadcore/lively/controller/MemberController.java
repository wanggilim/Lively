package com.quadcore.lively.controller;

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
		
		//멤버 레벨 얻기
		public MemberVO selectByUserLevel(int userNo) {
			// TODO Auto-generated method stub
			return service.selectByUserLevel(userNo);
		}
		//멤버 삭제
		public Object deleteUserFromUserMail(String userMail) {
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

	

	
	

}
