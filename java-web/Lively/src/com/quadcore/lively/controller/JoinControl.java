package com.quadcore.lively.controller;

import com.quadcore.lively.model.MemberVO;
import com.quadcore.lively.service.JoinService;



public class JoinControl {

		
		private JoinService service;
		
		public JoinControl() {
			service = new  JoinService();
		}
		// TODO Auto-generated method stub
		//로그인 승인
		public int getUserAuth(String usermail, String userpass) {
			return service.getUserAuth(usermail, userpass);
}
		//가입시 기존 유뮤 확인
		public int getUserMail(String userMail) {
			return service.getUserMail(userMail);
		}

	

	
	

}
