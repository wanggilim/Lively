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
		

		public int getUserLevel(String userMail) {
			// TODO Auto-generated method stub
			return service.getUserLevel(userMail);
		}

		public Object deleteUserFromUserMail(String userMail) {
			return service.deleteUserFromUserMail(userMail);
			
		}

		public Object updateUserFromUserMail(String userMail) {
			return service.updateUserFromUserMail(userMail);
			
		}

		public Object searchUserFromUserMail(String userMail) {
			return service.searchUserFromUserMail(userMail);
			
		}

	

	
	

}
