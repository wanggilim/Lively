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
		
		/**
		 * 사용자의 메일주소만을 입력하여 MemberVO 객체를 반환함.
		 * @author wgl
		 * @Date 2018.12.14
		 * @param userMail
		 * @return MemberVO
		 */
		public MemberVO getMember(String userMail) {
			return service.getMember(userMail);
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
